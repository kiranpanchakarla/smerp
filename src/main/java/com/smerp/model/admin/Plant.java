package com.smerp.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.UserAuditModel;



@Entity
@Table(name="tbl_admin_plant")
public class Plant extends UserAuditModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="plant_id" , nullable = false, unique = true)
	private Integer id;
	
	
	@Column(name="plant_name")
	private String  plantName;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPlantName() {
		return plantName;
	}


	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}


	@Override
	public String toString() {
		return "Plant [id=" + id + ", plantName=" + plantName + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
