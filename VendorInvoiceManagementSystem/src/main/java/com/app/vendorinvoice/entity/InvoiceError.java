package com.app.vendorinvoice.entity;

import java.time.OffsetTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "invoice_error")
@Data
public class InvoiceError {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "error_id")
	private Long errorId;
	
	@Column(name = "invoice_row_number")
	private Long invoiceRowNumber;
	
	@Column(name = "column_name", length = 50)
	private String columnName;
	
	@Column(name = "error_message", length = 500)
	private String errorMessage;
	
	@Column(name = "upload_batch_id", length = 50)
	private String uploadBatchId;
	
	@Column(name = "created_date")
	private OffsetTime createdDate;

}
