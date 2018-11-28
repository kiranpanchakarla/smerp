package com.smerp.repository.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_admin_accesspermission")
public class AccessPermission {
	
	@Id
    @Column(name="accesspermission_id", nullable = false, unique=true)
    @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	@Column(name="read")
	private String read;
	
	@Column(name="write")
	private String write;
	
	@Column(name="update")
	private String update;
	
	@Column(name="delete")
	private String delete;
	
	
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getWrite() {
		return write;
	}
	public void setWrite(String write) {
		this.write = write;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getDelete() {
		return delete;
	}
	public void setDelete(String delete) {
		this.delete = delete;
	}
	@Override
	public String toString() {
		return "AccessPermission [read=" + read + ", write=" + write + ", update=" + update + ", delete=" + delete
				+ "]";
	}
	
	
	

}
