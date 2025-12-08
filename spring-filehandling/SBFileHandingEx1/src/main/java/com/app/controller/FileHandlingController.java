package com.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file/")
public class FileHandlingController {

	private static final String FIlE_PATH = "D:" + File.separator + "Rahul" 
	+ File.separator + "file uploads" +  File.separator;

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		String fileUploadStatus;
		try {
			FileOutputStream fos = new FileOutputStream(FIlE_PATH + file.getOriginalFilename());
			fos.write(file.getBytes());
			fos.close();
	         fileUploadStatus = "File Uploaded Successfully";
			} catch (Exception e) {
				e.printStackTrace();
				fileUploadStatus = "Error in uploading file: " + e;
			}
		return file.getOriginalFilename() +" : "+ fileUploadStatus + ": " + file.getContentType();
	}
	
	@GetMapping("/getFiles")
    public String[] getFiles() {
		File directory = new File(FIlE_PATH);
		String[] fileName = directory.list();
		return fileName;
	}
	
	@GetMapping("/download/{filename}")
	public ResponseEntity downloadFile(@PathVariable("filename") String filename) throws FileNotFoundException {
		
		String[] filesNames = this.getFiles();
		boolean contains = Arrays.asList(filesNames).contains(filename);
		if(!contains) {
			return new ResponseEntity("File Not Found", HttpStatus.NOT_FOUND);
		}
		
		String filePath = FIlE_PATH + filename;
		
		// Creating new file instance
        File file= new File(filePath);
		
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        
     // Creating a new instance of HttpHeaders Object
        HttpHeaders headers = new HttpHeaders();
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
        
		 return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				 .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
				 .body(resource);
	}
	
}
