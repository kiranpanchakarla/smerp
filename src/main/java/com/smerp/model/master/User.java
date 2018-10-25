package com.smerp.model.master;
/*package com.smerp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable {
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", user_verified=" + user_verified + ", firstName="
				+ firstName + ", lastName=" + lastName + ", gender=" + gender + ", user_password=" + user_password
				+ "]";
	}

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;
	
	
    @Id
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer userId;
    
    @Column(name="user_name")
	private String userName;
    
	//private String user_email;
    
    
    private Boolean user_verified;
    
    

	
    @Column(name="first_name")
	private String firstName;
    
    @Column(name="last_Name")
	private String lastName;
    
    @Column(name="gender")
	private String gender;
    
    //private Date createdDate;
	//private Date updatedDate;
	

    
	private String user_password;
    

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
	
	
	
	

	
	
}
*/