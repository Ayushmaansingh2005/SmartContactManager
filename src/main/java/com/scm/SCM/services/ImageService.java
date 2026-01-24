package com.scm.SCM.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public String uploadImage(MultipartFile contact_image,String filename);

    String getUrlFromPublicId(String piblicId);
}
