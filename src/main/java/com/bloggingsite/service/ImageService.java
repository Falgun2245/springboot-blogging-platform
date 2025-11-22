package com.bloggingsite.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String imageName(String path , MultipartFile multipartFile) throws IOException;
}