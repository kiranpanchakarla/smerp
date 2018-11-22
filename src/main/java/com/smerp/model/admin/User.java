package com.smerp.model.admin;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smerp.model.master.AuditModel;


@Entity
@Table(name="tbl_admin_user")
public class User extends AuditModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="user_id", nullable= false, unique=true )
	private Integer userId;
	
	@Column(name="user_name")
	private String username;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="password")
	private String password;
	

	private Boolean enabled = true;
	
	@Column(name="first_name")
	private String firstname;
	
	@Column(name="last_name")
	private String lastname;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	//@Transient
	//@NotEmpty(message = "Confirm Password cannot be empty")
	@Column(name="conform_password")
	private transient  String confirmPassword;
	//email activation 
	@Column(name="activation_id")
	private String activationId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="company_id")
	private Company company;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="department_id")
	private Department department;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="desigination_id")
	private Desigination designation;
	
	
	@Column(name="reporting_manager_id")
	private Integer reportingManagerId;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plant_id")
	private Plant plant;
	
	
	@Column(name="image")
	private String image;
	
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "user_roles", joinColumns = {
	            @JoinColumn(name = "user_id") }, inverseJoinColumns = {
	            @JoinColumn(name = "role_id") })
   private Set<Role> roles;
  
 
  
	private transient String rolesDt;
	
	


	public String getRolesDt() {
		return rolesDt;
	}

	public void setRolesDt(String rolesDt) {
		this.rolesDt = rolesDt;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getActivationId() {
		return activationId;
	}

	public void setActivationId(String activationId) {
		this.activationId = activationId;
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

	public Desigination getDesigination() {
		return designation;
	}

	public void setDesigination(Desigination designation) {
		this.designation = designation;
	}

	public Integer getReportingManagerId() {
		return reportingManagerId;
	}

	public void setReportingManagerId(Integer reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}



	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", userEmail=" + userEmail + ", password="
				+ password + ", enabled=" + enabled + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", mobileNo=" + mobileNo + ", activationId=" + activationId + ", company=" + company + ", department="
				+ department + ", designation=" + designation + ", reportingManagerId=" + reportingManagerId
				+ ", plant=" + plant + ", image=" + image + ", roles=" + roles + "]";
	}

	


	

	

	
	


}