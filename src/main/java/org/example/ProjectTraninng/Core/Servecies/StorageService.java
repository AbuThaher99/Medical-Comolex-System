package org.example.ProjectTraninng.Core.Servecies;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Core.Repsitories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private WarehouseStoreRepository warehouseStoreRepository;

    @Autowired
    private UserRepository userRepository;
    String imageFolder = "C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\Images\\";
    String excelFolder = "C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\Excels\\";
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=imageFolder+file.getOriginalFilename();

        ImageData fileData=fileDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public ImageData downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<ImageData> fileData = fileDataRepository.findByName(fileName);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getFilePath();
            byte[] images = Files.readAllBytes(new File(filePath).toPath());
            fileData.get().setData(images);
            return fileData.get();
        }
        throw new IOException("File not found");
    }

    public String GanratePationToExcel() throws FileNotFoundException {
        Workbook workbook = new HSSFWorkbook();
        String filePath = excelFolder + "PationData"+ System.currentTimeMillis() + ".xls";
        List<Patients> pations = patientRepository.findAll();
        Sheet sheet = workbook.createSheet("Patients");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Address");
        headerRow.createCell(2).setCellValue("Age");
        headerRow.createCell(3).setCellValue("Created Date");
        headerRow.createCell(4).setCellValue("First Name");
        headerRow.createCell(5).setCellValue("Last Name");
        headerRow.createCell(6).setCellValue("Phone");

        for (int i = 0; i < pations.size(); i++) {
            Patients patient = pations.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(patient.getId());
            row.createCell(1).setCellValue(patient.getAddress());
            row.createCell(2).setCellValue(patient.getAge());
            row.createCell(3).setCellValue(patient.getCreatedDate().toString());
            row.createCell(4).setCellValue(patient.getFirstName());
            row.createCell(5).setCellValue(patient.getLastName());
            row.createCell(6).setCellValue(patient.getPhone());
        }
        try (OutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            ImageData fileData = fileDataRepository.save(ImageData.builder()
                    .name("PationData"+ System.currentTimeMillis() + ".xls")
                    .type("application/vnd.ms-excel")
                    .filePath(filePath).build());

            return  filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String GanarateMedicineToExcel() throws FileNotFoundException {
        Workbook workbook = new HSSFWorkbook();
        String filePath = excelFolder + "MedicineData"+ System.currentTimeMillis() + ".xls";
        List<Medicine> medicines = medicineRepository.findAll();
        Sheet sheet = workbook.createSheet("Medicines");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Buy Price");
        headerRow.createCell(3).setCellValue("Purchase Price");
        headerRow.createCell(4).setCellValue("Expiration Date");
        headerRow.createCell(5).setCellValue("Created Date");
        headerRow.createCell(6).setCellValue("Quantity");

        for (int i = 0; i < medicines.size(); i++) {
            Medicine medicine = medicines.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(medicine.getId());
            row.createCell(1).setCellValue(medicine.getName());
            row.createCell(2).setCellValue(medicine.getBuyPrice());
            row.createCell(3).setCellValue(medicine.getPurchasePrice());
            row.createCell(4).setCellValue(medicine.getExpirationDate().toString());
            row.createCell(5).setCellValue(medicine.getCreatedDate().toString());
            WarehouseStore warehouseStore = warehouseStoreRepository.findByMedicineId(medicine.getId());
            if (warehouseStore == null) {
                warehouseStore = WarehouseStore.builder().quantity(0).build();
            }
            row.createCell(6).setCellValue(warehouseStore.getQuantity());

        }
        try (OutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            ImageData fileData = fileDataRepository.save(ImageData.builder()
                    .name("MedicineData"+ System.currentTimeMillis() + ".xls")
                    .type("application/vnd.ms-excel")
                    .filePath(filePath).build());

            return  filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public String GanaratePatientTreatmentToExcel() throws FileNotFoundException {
        Workbook workbook = new HSSFWorkbook();
        String filePath = excelFolder + "PatientTreatmentData" + System.currentTimeMillis() + ".xls";
        List<Patients> patientTreatments = patientRepository.findAll();
        Sheet sheet = workbook.createSheet("PatientTreatments");

        int rowIndex = 0;

        CellStyle patientHeaderStyle = workbook.createCellStyle();
        CellStyle treatmentHeaderStyle = workbook.createCellStyle();
        CellStyle medicineHeaderStyle = workbook.createCellStyle();
        CellStyle dateCellStyle = workbook.createCellStyle();
        CellStyle dataCellStyle = workbook.createCellStyle();

        patientHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
        treatmentHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
        medicineHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
        dateCellStyle.setAlignment(HorizontalAlignment.CENTER);
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);

        CreationHelper creationHelper = workbook.getCreationHelper();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd"));

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        Font headerFont2 = workbook.createFont();
        headerFont2.setBold(true);

        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 30 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 10 * 256);
        sheet.setColumnWidth(9, 15 * 256);

        patientHeaderStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        patientHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        patientHeaderStyle.setFont(headerFont);

        treatmentHeaderStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        treatmentHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        treatmentHeaderStyle.setFont(headerFont2);

        medicineHeaderStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        medicineHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        medicineHeaderStyle.setFont(headerFont2);

        for (Patients patient : patientTreatments) {
            if (rowIndex > 0) {
                rowIndex++;
            }


            Row patientHeaderRow = sheet.createRow(rowIndex++);
            patientHeaderRow.setRowStyle(dataCellStyle);
            Cell patientHeaderCell1 = patientHeaderRow.createCell(0);
            patientHeaderCell1.setCellValue("Patient Name");
            patientHeaderCell1.setCellStyle(patientHeaderStyle);
            Cell patientHeaderCell2 = patientHeaderRow.createCell(1);
            patientHeaderCell2.setCellValue("Age");
            patientHeaderCell2.setCellStyle(patientHeaderStyle);
            Cell patientHeaderCell3 = patientHeaderRow.createCell(2);
            patientHeaderCell3.setCellValue("Address");
            patientHeaderCell3.setCellStyle(patientHeaderStyle);
            Cell patientHeaderCell4 = patientHeaderRow.createCell(3);
            patientHeaderCell4.setCellValue("Phone");
            patientHeaderCell4.setCellStyle(patientHeaderStyle);

            Row patientRow = sheet.createRow(rowIndex++);
            Cell patientCell1 = patientRow.createCell(0);
            patientCell1.setCellValue(patient.getFirstName() + " " + patient.getLastName());
            patientCell1.setCellStyle(dataCellStyle);
            Cell patientCell2 = patientRow.createCell(1);
            patientCell2.setCellValue(patient.getAge());
            patientCell2.setCellStyle(dataCellStyle);
            Cell patientCell3 = patientRow.createCell(2);
            patientCell3.setCellValue(patient.getAddress());
            patientCell3.setCellStyle(dataCellStyle);
            Cell patientCell4 = patientRow.createCell(3);
            patientCell4.setCellValue(patient.getPhone());
            patientCell4.setCellStyle(dataCellStyle);

            List<Treatment> treatments = patient.getTreatments();
            if (treatments != null && !treatments.isEmpty()) {
                Row treatmentHeaderRow = sheet.createRow(rowIndex++);
                treatmentHeaderRow.setRowStyle(dataCellStyle);
                Cell treatmentHeaderCell1 = treatmentHeaderRow.createCell(0);
                treatmentHeaderCell1.setCellValue("Treatment Date");
                treatmentHeaderCell1.setCellStyle(treatmentHeaderStyle);
                Cell treatmentHeaderCell2 = treatmentHeaderRow.createCell(1);
                treatmentHeaderCell2.setCellValue("Doctor Name");
                treatmentHeaderCell2.setCellStyle(treatmentHeaderStyle);
                Cell treatmentHeaderCell3 = treatmentHeaderRow.createCell(2);
                treatmentHeaderCell3.setCellValue("Disease Description");
                treatmentHeaderCell3.setCellStyle(treatmentHeaderStyle);
                Cell treatmentHeaderCell4 = treatmentHeaderRow.createCell(3);
                treatmentHeaderCell4.setCellValue("Note");
                treatmentHeaderCell4.setCellStyle(treatmentHeaderStyle);

                for (Treatment treatment : treatments) {

                    Row treatmentRow = sheet.createRow(rowIndex++);
                    treatmentRow.setRowStyle(dataCellStyle);
                    Cell dateCell = treatmentRow.createCell(0);
                    dateCell.setCellValue(treatment.getTreatmentDate());
                    dateCell.setCellStyle(dateCellStyle);
                    Cell treatmentCell1 = treatmentRow.createCell(1);
                    treatmentCell1.setCellValue(treatment.getDoctor().getUser().getFirstName() + " " + treatment.getDoctor().getUser().getLastName());
                    treatmentCell1.setCellStyle(dataCellStyle);
                    Cell treatmentCell2 = treatmentRow.createCell(2);
                    treatmentCell2.setCellValue(treatment.getDiseaseDescription());
                    treatmentCell2.setCellStyle(dataCellStyle);
                    Cell treatmentCell3 = treatmentRow.createCell(3);
                    treatmentCell3.setCellValue(treatment.getNote());
                    treatmentCell3.setCellStyle(dataCellStyle);

                    List<PatientMedicine> patientMedicines = treatment.getPatientMedicines();
                    if (patientMedicines != null && !patientMedicines.isEmpty()) {
                        Row medicineHeaderRow = sheet.createRow(rowIndex++);
                        Cell medicineHeaderCell1 = medicineHeaderRow.createCell(1);
                        medicineHeaderCell1.setCellValue("Medicine Name");
                        medicineHeaderCell1.setCellStyle(medicineHeaderStyle);
                        Cell medicineHeaderCell2 = medicineHeaderRow.createCell(2);
                        medicineHeaderCell2.setCellValue("Quantity");
                        medicineHeaderCell2.setCellStyle(medicineHeaderStyle);
                        Cell medicineHeaderCell3 = medicineHeaderRow.createCell(3);
                        medicineHeaderCell3.setCellValue("Price");
                        medicineHeaderCell3.setCellStyle(medicineHeaderStyle);

                        for (PatientMedicine patientMedicine : patientMedicines) {
                            Row medicineRow = sheet.createRow(rowIndex++);
                            Cell medicineCell1 = medicineRow.createCell(1);
                            medicineCell1.setCellValue(patientMedicine.getMedicine().getName());
                            medicineCell1.setCellStyle(dataCellStyle);
                            Cell medicineCell2 = medicineRow.createCell(2);
                            medicineCell2.setCellValue(patientMedicine.getQuantity());
                            medicineCell2.setCellStyle(dataCellStyle);
                            Cell medicineCell3 = medicineRow.createCell(3);
                            medicineCell3.setCellValue(patientMedicine.getPrice());
                            medicineCell3.setCellStyle(dataCellStyle);
                        }
                    }
                }
            }
        }

        try (OutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            ImageData fileData = fileDataRepository.save(ImageData.builder()
                    .name("PatientTreatmentData" + System.currentTimeMillis() + ".xls")
                    .type("application/vnd.ms-excel")
                    .filePath(filePath).build());

            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }





}
