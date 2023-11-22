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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ImageController {
	@Autowired
	ImageRepository imageRepository;

	@Operation(description = "Image Object Will be Upload...", summary = "To Save Image object to the Database...")
	@ApiResponses(value = { @ApiResponse(description = "Image Saved Successfully", responseCode = "201"),
			@ApiResponse(description = "Unable To Save image To Database", responseCode = "422") })
	@PostMapping("/saveImage")
	public ResponseEntity<ResponseStructure<Image>> uploadImage(@RequestParam MultipartFile file) throws IOException {
		Image m = new Image();
		m.setImage(file.getBytes());
		Image image = imageRepository.save(m);
		return new ResponseEntity<ResponseStructure<Image>>(new ResponseStructure<Image>(), HttpStatus.CREATED);
	}
}
