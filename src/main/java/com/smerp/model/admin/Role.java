package com.smerp.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smerp.model.master.UserAuditModel;

@Entity
@Table(name="tbl_admin_role")
public class Role extends UserAuditModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="role_id", nullable = false, unique=true)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="role_name")
    private String name;

    @Column(name="description")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
    
    
}
