package com.app.vendorinvoice.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.vendorinvoice.entity.DocumentMetaData;
import com.app.vendorinvoice.repository.DocumentMetaDataRepository;
import com.app.vendorinvoice.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	private final DocumentMetaDataRepository documentMetaDataRepository;

	public FileUploadServiceImpl(DocumentMetaDataRepository documentMetaDataRepository) {
		this.documentMetaDataRepository = documentMetaDataRepository;
	}
	
	@Override
	public DocumentMetaData uploadFile(MultipartFile file) throws IOException {
		@Nullable
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();
		Long size = file.getSize();
		byte[] bytes = file.getBytes();
		DocumentMetaData fs = new DocumentMetaData(fileName, contentType, size, bytes, LocalDateTime.now());
        return documentMetaDataRepository.save(fs);
    }
	
	@Override
    public DocumentMetaData getFile(Long id) {
        return documentMetaDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

	@Override
    public DocumentMetaData updateFile(Long id, MultipartFile file) throws IOException {
    	DocumentMetaData fs = getFile(id);
        fs.setFileName(file.getOriginalFilename());
        fs.setFileType(file.getContentType());
        fs.setFileSize(file.getSize());
        fs.setFileData(file.getBytes());
        return documentMetaDataRepository.save(fs);
    }

	@Override
    public void deleteFile(Long id) {
    	documentMetaDataRepository.deleteById(id);
    }

}
