package org.example.ProjectTraninng.Core.Servecies;

import org.example.ProjectTraninng.Common.Entities.ImageData;
import org.example.ProjectTraninng.Core.Repsitories.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private FileDataRepository fileDataRepository;
    String imageFolder = "C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\Images\\";

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
}
