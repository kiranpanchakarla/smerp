package com.smerp.model.master;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smerp.model.admin.User;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt","createdBy","lastModifiedBy"},
        allowGetters = true
)

public class AuditModel  implements Serializable {

    
    @ManyToOne(fetch = FetchType.LAZY)
  	@JoinColumn(name="created_uid" , referencedColumnName = "user_id", nullable = false, updatable = false)
    @CreatedBy
    private User createdBy;


    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="updated_uid" , referencedColumnName = "user_id")
    @LastModifiedBy
    private User lastModifiedBy;


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
    
    
    
    @PrePersist
    public void onSave()
    {
     
    	 if (SecurityContextHolder.getContext().getAuthentication() != null) {
			 User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 this.createdBy = user;
			 this.lastModifiedBy = user;
    	 }else {
    		 this.createdBy = null;
    	 }
    	
    }

    @PreUpdate
    public void onUpdate()
    {
      
    	 if (SecurityContextHolder.getContext().getAuthentication() != null) {
			 User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 this.lastModifiedBy = user;
			 
    	 }else {
    		 this.lastModifiedBy = null;
    	 }
    	
    }


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	@Override
	public String toString() {
		return "Auditable [createdBy=" + createdBy + ", createdAt=" + createdAt + ", lastModifiedBy=" + lastModifiedBy
				+ ", updatedAt=" + updatedAt + "]";
	}
    
    
    
    
    
}
