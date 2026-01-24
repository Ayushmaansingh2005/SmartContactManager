package com.scm.SCM.services.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.SCM.helpers.AppConstants;
import com.scm.SCM.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {


    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    
    @Override
    public String uploadImage(MultipartFile contact_image , String filename) {
       
        //write the code for uploading image

        try {
            byte[] data = new byte[contact_image.getInputStream().available()];
            contact_image.getInputStream().read(data);
            cloudinary.uploader().upload(data,ObjectUtils.asMap(
                "public_id",filename
            ));
             System.out.println("The image is saved successfully");
            return this.getUrlFromPublicId(filename);
            
        } catch (IOException e) {
            System.out.println("The image is not saved");
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String getUrlFromPublicId(String publicId) {
       return cloudinary
                .url()
                .transformation(
                        new Transformation<>()
                                .width(AppConstants.CONTACT_IMAGE_WIDTH)
                                .height(AppConstants.CONTACT_IMAGE_HIEGHT)
                                .crop(AppConstants.CONTACT_IMAGE_CROP))
                .generate(publicId);                
    }


    
}
