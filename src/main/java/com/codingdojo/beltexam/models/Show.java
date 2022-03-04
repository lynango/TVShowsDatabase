package com.codingdojo.beltexam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="shows")
public class Show {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
				
		@NotEmpty(message="Unique title is required")
		@Size(min=5, max= 50, message="Please provide a unique title between 5-50 characters")
		private String title;
				
		@NotEmpty(message="Name of network is required")
		@Size(min = 3, max = 50, message="Please provide a network between 3-50 characters")
		private String network;
		
		@NotEmpty(message="Description is required")
		@Size(min = 5, max = 250, message="Please provide a description between 5-250 characters")
		private String description;
				
		@Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
	    
	    @PrePersist
	    protected void onCreate() {
	    	this.createdAt=new Date();
	    }
	    
		@PreUpdate
		protected void onUpdate() {
		this.updatedAt=new Date();
		}
	
// Relationships - Might come back to change ALL to REMOVE later
	   
	    @OneToMany(mappedBy="show",fetch=FetchType.LAZY,cascade=CascadeType.ALL) 
		 private List<Rating> ratings;
	    
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name="creator_id")
	    private User creator;
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(
		     name = "users_shows", 
		     joinColumns = @JoinColumn(name = "show_id"), 
		     inverseJoinColumns = @JoinColumn(name = "user_id"))
			private List<User> users;
	
// Constructor
		
	public Show() {		
	}
	
// Getters and Setters	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
		
}