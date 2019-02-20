package com.smerp.model.master;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smerp.model.admin.User;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
		 value = {"createdAt", "updatedAt","createdBy","updatedBy"},
        allowGetters = true
)
public abstract class UserAuditModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
    
    @Column(name="created_uid", nullable = false, updatable = false)
    private Integer createdBy;
	 
    @Column(name="updated_uid")
    private Integer updatedBy;
    
    @PrePersist
    public void onSave()
    {
     
    	 if (SecurityContextHolder.getContext().getAuthentication() != null) {
			 User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 this.createdBy = user.getUserId();
			 this.updatedBy = user.getUserId();
    	 }else {
    		 this.createdBy = null;
    	 }
    	
    }

    @PreUpdate
    public void onUpdate()
    {
      
    	 if (SecurityContextHolder.getContext().getAuthentication() != null) {
			 User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 this.updatedBy = user.getUserId();
			 
    	 }else {
    		 this.updatedBy = null;
    	 }
    	
    }
    
    

    
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "UserAuditModel [createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + "]";
	}



	
}