package com.api.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.helper.FileUploadHelper;

@RestController
public class fileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> saveFile(@RequestParam("myimg") MultipartFile myimg) {
//		
//		System.out.println(myimg.getOriginalFilename());
//		System.out.println(myimg.getClass());

		
		//validation
		try {
			
			//is Empty validation
			if (myimg.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");

			}
			//

			if (!myimg.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Only jpeg content type are allow..!");

			}
			// file upload code
			
			boolean f = fileUploadHelper.uploadFile(myimg);
			
			if(f) {
				
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image").path(myimg.getOriginalFilename()).toUriString());
	
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Somthing went wrong...! Try again");
	}
}
