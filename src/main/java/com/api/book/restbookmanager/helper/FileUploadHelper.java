package com.api.book.restbookmanager.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    // final String UPLOAD_DIR = "C:\\Users\\avishwakarma\\eclipse-workspace\\bootrestbook\\src\\main\\resources\\static\\images";
    final String UPLOAD_DIR= new ClassPathResource("/static/images").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException{
    }

    public boolean uploadFileHelp(MultipartFile mFile){

        
        try {
            
            Files.copy(mFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+mFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }
    
}
