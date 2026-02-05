package com.app.vendorinvoice.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.vendorinvoice.entity.DocumentMetaData;

public interface FileUploadService {

	public DocumentMetaData uploadFile(MultipartFile file) throws IOException;

	public DocumentMetaData getFile(Long id);

	public DocumentMetaData updateFile(Long id, MultipartFile file) throws IOException;

	public void deleteFile(Long id);
}
