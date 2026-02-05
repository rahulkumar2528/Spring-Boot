package com.app.vendorinvoice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "upload_batch")
public class UploadBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_id")
	private Long batchId;

	@Column(name = "uploaded_by", length = 50)
	private String uploadedBy;

	@Column(name = "upload_time")
	private LocalDateTime uploadTime;

	@Column(name = "total_records")
	private Long totalRecords;

	@Column(name = "success_count")
	private Long successCount;

	@Column(name = "failure_count")
	private Long failureCount;

	@Column(name = "status")
	private Short status;

}
