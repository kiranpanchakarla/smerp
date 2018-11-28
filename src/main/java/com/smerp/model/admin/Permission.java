package com.smerp.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_admin_permission")
public class Permission {
	
	@Id
    @Column(name="permission_id", nullable = false, unique=true)
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	@Column(name="permissionName")
	private String permissionName;
	
	private transient String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionName=" + permissionName + "]";
	}
	

	
	
	

}
