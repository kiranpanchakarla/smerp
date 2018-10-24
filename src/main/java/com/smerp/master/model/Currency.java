package com.smerp.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_currencies_master")
public class Currency extends AuditModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Column(name="currencies_id" , nullable= false, unique=true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="description")
    private String description;
    
   
        
    @Column(name="is_active")
    private Boolean isActive;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


   

    public Boolean getIsActive() {
        return isActive;
    }


    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


	@Override
	public String toString() {
		return "Currency [id=" + id + ", name=" + name + ", description=" + description + ", isActive=" + isActive
				+ "]";
	}
    
   

}