package com.smerp.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import com.smerp.model.master.UserAuditModel;


@Entity
@Table(name="tbl_admin_designations")
public class Desigination extends UserAuditModel	 {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="desigination_id" , nullable = false , unique = true)
	private Integer id;
	
	@Column(name="desigination")
	private String desigination;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="company_id")
	private Company company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;
	
	@Column(name="expected_employees")
	@Max(value=5)
	private Integer expectedEmployees;
	
	@Column(name="description")
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesigination() {
		return desigination;
	}

	public void setDesigination(String desigination) {
		this.desigination = desigination;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getExpectedEmployees() {
		return expectedEmployees;
	}

	public void setExpectedEmployees(Integer expectedEmployees) {
		this.expectedEmployees = expectedEmployees;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Desigination [id=" + id + ", desigination=" + desigination + ", company=" + company + ", department="
				+ department + ", expectedEmployees=" + expectedEmployees + ", description=" + description + "]";
	}
	
	
	

}
