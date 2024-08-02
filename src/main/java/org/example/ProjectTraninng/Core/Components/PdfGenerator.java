package org.example.ProjectTraninng.Core.Components;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.Data;
import org.example.ProjectTraninng.Common.Entities.ImageData;
import org.example.ProjectTraninng.Common.Entities.SalaryPayment;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Core.Repsitories.FileDataRepository;
import org.example.ProjectTraninng.Core.Servecies.SalaryPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
public class PdfGenerator {

    @Autowired
    private SalaryPaymentService salaryPaymentService;

    @Autowired
    private ChartGenerator chartGenerator;

    @Autowired
    private FileDataRepository fileDataRepository;

    String pdfFiles = "C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\PDF\\";
    String chartFiles = "C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\Chart\\";


    public String generatePdfForRoles(List<Role> roles, String headerBgColor, String headerTextColor, String tableRowColor1, String tableRowColor2) throws Exception {
        List<SalaryPayment> payments = salaryPaymentService.getSalaryPaymentsByRoles(roles);
        Map<String, Double> totalAmountByMonth = salaryPaymentService.getTotalAmountByMonth(payments);
        double totalAmount = salaryPaymentService.getTotalAmount(payments);
        Long time = System.currentTimeMillis();
        String pdfFilePath = pdfFiles + "SalaryPaymentsReport" + time + ".pdf";
        String chartFilePath = chartFiles + "chart" + time + ".png";

        chartGenerator.generateChart(totalAmountByMonth, chartFilePath);

        Document document = new Document(PageSize.A4, 36, 36, 100, 36);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        writer.setPageEvent(new HeaderFooterPageEvent());

        document.open();

        Paragraph title = new Paragraph("Salary Payments Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph rolesParagraph = new Paragraph("Roles: " + roles);
        rolesParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(rolesParagraph);

        Paragraph totalAmountParagraph = new Paragraph("Total Amount: $" + totalAmount);
        totalAmountParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(totalAmountParagraph);

        List<User> users = salaryPaymentService.getUsersByRoles(roles);
        document.add(new Paragraph("Users Information:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        document.add(new Paragraph(" "));
        PdfPTable userTable = new PdfPTable(6);
        userTable.setWidthPercentage(100);
        userTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        userTable.setSpacingBefore(10);
        userTable.setSpacingAfter(10);

        // Customizable header cell colors
        addColoredHeaderCell(userTable, "ID", hexToBaseColor(headerBgColor), hexToBaseColor(headerTextColor));
        addColoredHeaderCell(userTable, "First Name", hexToBaseColor(headerBgColor), hexToBaseColor(headerTextColor));
        addColoredHeaderCell(userTable, "Last Name", hexToBaseColor(headerBgColor), hexToBaseColor(headerTextColor));
        addColoredHeaderCell(userTable, "Email", hexToBaseColor(headerBgColor), hexToBaseColor(headerTextColor));
        addColoredHeaderCell(userTable, "Role", hexToBaseColor(headerBgColor), hexToBaseColor(headerTextColor));
        addColoredHeaderCell(userTable, "Total Salary", hexToBaseColor(headerBgColor), hexToBaseColor(headerTextColor));

        int rowIndex = 0;
        for (User user : users) {
            addColoredRow(userTable, user, rowIndex++, hexToBaseColor(tableRowColor1), hexToBaseColor(tableRowColor2));
        }
        document.add(userTable);

        document.add(new Paragraph("Total Amount by Month:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        document.add(new Paragraph(" "));
        PdfPTable monthTable = new PdfPTable(2);
        monthTable.setWidthPercentage(80);
        monthTable.setHorizontalAlignment(Element.ALIGN_CENTER);

        addColoredHeaderCell(monthTable, "Month", hexToBaseColor(headerBgColor), hexToBaseColor(headerTextColor));
        addColoredHeaderCell(monthTable, "Total Amount", hexToBaseColor(headerBgColor), hexToBaseColor(headerTextColor));

        rowIndex = 0;
        for (Map.Entry<String, Double> entry : totalAmountByMonth.entrySet()) {
            addColoredRow(monthTable, entry, rowIndex++, hexToBaseColor(tableRowColor1), hexToBaseColor(tableRowColor2));
        }
        document.add(monthTable);

        Image chartImage = Image.getInstance(chartFilePath);
        chartImage.setAlignment(Image.ALIGN_CENTER);
        chartImage.scaleToFit(PageSize.A4.getWidth() - 72, PageSize.A4.getHeight() - 72);
        document.add(chartImage);

        fileDataRepository.save(ImageData.builder()
                .name("chart" + time + ".png")
                .type("image/png")
                .filePath(chartFilePath).build());

        fileDataRepository.save(ImageData.builder()
                .name("SalaryPaymentsReport" + time + ".pdf")
                .type("application/pdf")
                .filePath(pdfFilePath).build());

        document.close();
        try (FileOutputStream fos = new FileOutputStream(pdfFilePath)) {
            baos.writeTo(fos);
        }
        return pdfFilePath;
    }

    private String getTotalSalary(User user) {
        List<SalaryPayment> payments = user.getSalaryId();
        double totalSalary = payments.stream()
                .mapToDouble(SalaryPayment::getAmount)
                .sum();
        return "$" + totalSalary;
    }

    private void addColoredHeaderCell(PdfPTable table, String text, BaseColor backgroundColor, BaseColor textColor) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, textColor);
        Phrase phrase = new Phrase(text, font);
        PdfPCell cell = new PdfPCell(phrase);
        cell.setBackgroundColor(backgroundColor);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderWidth(2);
        cell.setBorderColor(BaseColor.BLACK);
        table.addCell(cell);
    }

    private void addColoredRow(PdfPTable table, User user, int rowIndex, BaseColor color1, BaseColor color2) {
        BaseColor color = (rowIndex % 2 == 0) ? color1 : color2;
        addCell(table, user.getId().toString(), color);
        addCell(table, user.getFirstName(), color);
        addCell(table, user.getLastName(), color);
        addCell(table, user.getEmail(), color);
        addCell(table, user.getRole().toString(), color);
        addCell(table, getTotalSalary(user), color);
    }

    private void addColoredRow(PdfPTable table, Map.Entry<String, Double> entry, int rowIndex, BaseColor color1, BaseColor color2) {
        BaseColor color = (rowIndex % 2 == 0) ? color1 : color2;
        addCell(table, entry.getKey(), color);
        addCell(table, "$" + entry.getValue(), color);
    }

    private void addCell(PdfPTable table, String text, BaseColor color) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA, 12)));
        cell.setBackgroundColor(color);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderWidth(2f);
        cell.setBorderColor(BaseColor.BLACK);
        table.addCell(cell);
    }

    private BaseColor hexToBaseColor(String hex) {
        return new BaseColor(
                Integer.valueOf(hex.substring(1, 3), 16),
                Integer.valueOf(hex.substring(3, 5), 16),
                Integer.valueOf(hex.substring(5, 7), 16)
        );
    }
    class HeaderFooterPageEvent extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable header = new PdfPTable(2);
            try {
                header.setWidths(new int[]{1, 3});
                header.setTotalWidth(527);
                header.setLockedWidth(true);
                header.getDefaultCell().setFixedHeight(50);
                header.getDefaultCell().setBorder(Rectangle.BOTTOM);
                header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

                LocalDate localDate = LocalDate.now();
                String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                PdfPCell dateCell = new PdfPCell(new Phrase("Date: " + formattedDate, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                dateCell.setBorder(Rectangle.BOTTOM);
                dateCell.setBorderColor(BaseColor.LIGHT_GRAY);
                dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                header.addCell(dateCell);

                PdfPCell titleCell = new PdfPCell(new Phrase("Salary Payments Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
                titleCell.setBorder(Rectangle.BOTTOM);
                titleCell.setBorderColor(BaseColor.LIGHT_GRAY);
                titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.addCell(titleCell);

                header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
    }
}
