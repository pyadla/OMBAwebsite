package com.ombawebsite.ItemFiles;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String dateOfBirth;
	private String customerId;
    private String mobileNo;
    private String permanentAddress;
    private String currentAddress;
    private String fathersName;
    private String nationality;
    private String sex;
    private String occupation;
    private String annualIncome;
    private String emailAddress;
	
    public Customer(String name,String dateOfBirth,String customerId,String mobileNo,
    		String permanentAddress,String currentAddress,String fathersName,String nationality,String sex,
    		String occupation,String annualIncome,String emailAddress) {
    	this.name = name;
    	this.dateOfBirth=dateOfBirth;
    	this.customerId=customerId;
		this.mobileNo=mobileNo;
		this.permanentAddress=permanentAddress;
		this.currentAddress=currentAddress;
		this.fathersName=fathersName;
		this.nationality=nationality;
		this.sex=sex;
		this.occupation=occupation;
		this.annualIncome=annualIncome;
		this.emailAddress=emailAddress;
	}
		
        public Long getId() {
			return id;
		}
		
        public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
        public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		
		public String getPermanentAddress() {
			return permanentAddress;
		}
		public void setPermanentAddress(String permanentAddress) {
			this.permanentAddress = permanentAddress;
		}
		
		public String getCurrentAddress() {
			return currentAddress;
		}
		public void setCurrentAddress(String currentAddress) {
			this.currentAddress = currentAddress;
		}
		
		public String getFathersName() {
			return fathersName;
		}
		public void setFathersName(String fathersName) {
			this.fathersName = fathersName;
		}
		
		public String getNationality() {
			return nationality;
		}
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}
		
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		
		public String getAnnualIncome() {
			return annualIncome;
		}
		public void setAnnualIncome(String annualIncome) {
			this.annualIncome = annualIncome;
		}
		
		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

	}



