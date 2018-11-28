package com.smerp.model.admin;


public class UPM {
	
	private Integer module_id;
	private String module_name;
	private Integer permssion_id;
	private String permission_name;

	private String user_access;
	private Integer id;
	public Integer getModule_id() {
		return module_id;
	}
	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}
	
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public Integer getPermssion_id() {
		return permssion_id;
	}
	public void setPermssion_id(Integer permssion_id) {
		this.permssion_id = permssion_id;
	}
	
	public String getPermission_name() {
		return permission_name;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}
	public String getUser_access() {
		return user_access;
	}
	public void setUser_access(String user_access) {
		this.user_access = user_access;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UPM [module_id=" + module_id + ", module_name=" + module_name + ", permssion_id=" + permssion_id
				+ ", permission_name=" + permission_name + ", user_access=" + user_access + ", id=" + id + "]";
	}
	
}
