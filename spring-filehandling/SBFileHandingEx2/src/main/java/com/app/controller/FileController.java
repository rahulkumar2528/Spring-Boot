package com.app.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.File;
import com.app.repository.FileRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("file/")
public class FileController {

	@Autowired
	FileRepository fileRepository;

	@Autowired
    HttpServletResponse response;
	
	@PostMapping("/upload")
	public String saveFile(@RequestParam("file") MultipartFile file) throws IOException {

		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		byte[] fileContent = file.getBytes();
		File savefile = new File(fileName, contentType, fileContent);
		fileRepository.save(savefile);
		return "File saved successfully";
	}
	
	@GetMapping("/getFiles")
	public List<File> getAllFile(){
		List<File> allFileList = fileRepository.findAll();
		return allFileList;
	}
	
	@GetMapping("/download")
	public String downloadFile(@RequestParam("id") String id) {
		File file = fileRepository.findById(id).get();
		byte[] data = file.getData();
		String name = file.getName();
		String type = file.getType();
		response.reset();
		response.setContentType(type);
		response.setHeader("content-disposition", "attachment; filename= "+name);
		try {
			BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
			os.write(data);
			os.close();
			return "File retrieved successfully";
		} catch (IOException e) {
			 return "Could not retrieve the file";
		}
		
	}
}
