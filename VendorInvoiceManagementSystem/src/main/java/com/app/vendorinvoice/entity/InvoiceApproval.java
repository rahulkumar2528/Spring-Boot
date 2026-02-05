package com.app.vendorinvoice.entity;

import java.time.OffsetTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "invoice_approval")
@Data
public class InvoiceApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "approval_id")
	private Long approvalId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id")
	private Invoice invoice;

	@Column(name = "approver_role", length = 40)
	private String approverRole;

	@Column(name = "status")
	private Short status;

	@Column(name = "remarks", length = 200)
	private String remarks;

	@Column(name = "approved_date")
	private OffsetTime approvedDate;

}
