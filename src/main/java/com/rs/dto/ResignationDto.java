package com.rs.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
public class ResignationDto {
	private Long id;
	private String dateOfApplying;
	private String reason;
	private String mobileNo;
	private String status;
	@JsonBackReference
	private EmployeeDto employee; // Reference to parent employee

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDateOfApplying() {
		return dateOfApplying;
	}
	public void setDateOfApplying(String dateOfApplying) {
		this.dateOfApplying = dateOfApplying;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}