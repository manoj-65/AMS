package com.ty.ams.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ty.ams.entity.Image;
import com.ty.ams.repository.ImageRepository;
import com.ty.ams.responsestructure.ResponseStructure;

@RestController
public class ImageController {
       @Autowired
	  ImageRepository imageRepository;
      
       
       @PostMapping("/saveImage")
       public ResponseEntity<ResponseStructure<Image>> uploadImage(@RequestParam MultipartFile file) throws IOException{
    	 System.out.println("dfgh");
    	   Image m= new Image();
			m.setImage(file.getBytes());
    	Image image =imageRepository.save(m);
    	   return new ResponseEntity<ResponseStructure<Image>>(new ResponseStructure<Image>(), HttpStatus.CREATED);
       }
}
