package com.bloggingsite.service.impl;

import com.bloggingsite.custom_exception.EmptyException;
import com.bloggingsite.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImp implements ImageService {

    @Override
    public String imageName(String path, MultipartFile multipartFile) throws IOException {

            if(multipartFile.isEmpty()) {

                throw new EmptyException("File is Empty!!!");
            }

            File uploadImages = new File(path);

            if(!uploadImages.exists()) {

                boolean created =  uploadImages.mkdirs();

                if(!created) {

                    throw new EmptyException("File is not created!!!");
                }
            }

            String fileName = multipartFile.getOriginalFilename();

            Path fullFilePath = Paths.get(path + File.separator +  fileName);

            Files.copy(multipartFile.getInputStream() , fullFilePath);

            return multipartFile.getName();
    }

    public String PostImage(String path , MultipartFile[] multipartFile) throws IOException{

        File postImage = new File(path);

        if(!postImage.exists()){

            boolean imageCreated = postImage.mkdirs();

            if(!imageCreated) {

                throw new RuntimeException("File is not created!!!");
            }
        }

        List<String> filesName = new ArrayList<>();

        for(MultipartFile file : multipartFile) {

            if(file.isEmpty()) {

                throw new EmptyException("File is empty!!!");
            }

            String fileName = file.getOriginalFilename();

            String fileFullPath = path + File.separator + fileName;

            Files.copy(file.getInputStream() , Path.of(fileFullPath));

            filesName.add(fileName);
        }
        return String.join("," , filesName);
    }
}