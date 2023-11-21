package com.api.book.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	//local machin path
//	public final String UPLOAD_DIR = "D:\\sts\\workspaceP\\restAPIBook\\src\\main\\resources\\static\\image";
	
	//static machine path
	public final String UPLOAD_DIR = new ClassPathResource("static/image").getFile().getAbsolutePath();
	
	public FileUploadHelper()throws IOException{
		
	}

	public boolean uploadFile(MultipartFile multipartFile) {
		boolean f = false;

		try {

			Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLOAD_DIR + "\\" + multipartFile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			 f = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

}
