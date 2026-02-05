package com.app.vendorinvoice.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.vendorinvoice.constants.Constants;
import com.app.vendorinvoice.entity.DocumentMetaData;
import com.app.vendorinvoice.service.FileUploadService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(Constants.CONT_DOCUMENT_UPLOAD)
public class DocumentUploadController {

	private final FileUploadService fileUploadService;

	public DocumentUploadController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadDocument(@RequestParam MultipartFile file) throws IOException {
		return ResponseEntity.ok(fileUploadService.uploadFile(file));
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable Long id){
		DocumentMetaData file = fileUploadService.getFile(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName())
				.contentType(MediaType.parseMediaType(file.getFileType()))
				.body(file.getFileData());
	}
}
