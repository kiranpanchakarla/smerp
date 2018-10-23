package com.smerp.jwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long user_id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
   

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> roles;

   

    public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
 
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public String getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", username=" + username + ", password=" + password + ", roles=" + roles
				+ "]";
	}
	
	
}
