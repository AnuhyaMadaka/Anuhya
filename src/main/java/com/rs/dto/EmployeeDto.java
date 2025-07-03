package com.rs.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeDto {
	private Long id;
	private String fullName;
	private String mobileNo;
	private String emailId;
	private String designation;
	private String empId;
	private String role;
	private String status;
	
	private List<LeaveRequestDto> leaveRequests;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<LeaveRequestDto> getLeaveRequests() {
		return leaveRequests;
	}
	public void setLeaveRequests(List<LeaveRequestDto> leaveRequests) {
		this.leaveRequests = leaveRequests;
	}
}
	
