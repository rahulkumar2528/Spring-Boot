package com.app.vendorinvoice.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "document_meta_data")
@Data
public class DocumentMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Long fileSize;
    

    @Lob
    @JdbcTypeCode(java.sql.Types.BINARY)
    @Column(name = "file_data", columnDefinition = "BYTEA")
    private byte[] fileData;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DocumentMetaData(String fileName, String fileType, Long fileSize, byte[] fileData,
			LocalDateTime createdAt) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.fileData = fileData;
		this.createdAt = createdAt;
	}

	public DocumentMetaData() {
	}
}
