package com.smerp.model.emailids;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_Email_Ids")
public class EmailId {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "module_name")
	private String moduleName;
	
	@Column(name = "operation")
	private String operation;
	
	@Column(name = "to_email")
	private String toEmail;
	
	@Column(name = "cc_email")
	private String ccEmail;
	
	@Column(name = "to_email_YML")
	private String toEmailYML;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	public String getToEmailYML() {
		return toEmailYML;
	}

	public void setToEmailYML(String toEmailYML) {
		this.toEmailYML = toEmailYML;
	}

	@Override
	public String toString() {
		return "EmailId [id=" + id + ", moduleName=" + moduleName + ", operation=" + operation + ", toEmail=" + toEmail
				+ ", ccEmail=" + ccEmail + ", toEmailYML=" + toEmailYML + "]";
	}

	 
	
	
}
