package com.ombawebsite.ItemFiles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;

	private String customerId;
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<SecurityQuestion> SQ=new ArrayList<SecurityQuestion>();

	public User(String customerId,String password,List<SecurityQuestion> SQ) {
		this.customerId=customerId;
		this.password=password;
		this.SQ= SQ;        
	}
	public Long getId() 
	{
		return id;
	}

	
	    public String getCustomerId() {
			return customerId;
		}
		
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public List<SecurityQuestion> getSQ() {
			return SQ;
		}
		
		public void setSQ(List<SecurityQuestion> SQ) {
			this.SQ = SQ;
		}
		
	}
	



