package com.smerp.model.admin;

import javax.persistence.*;

import com.smerp.model.master.AuditModel;

@Entity
@Table(name="tbl_admin_role")
public class Role extends AuditModel {

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
}