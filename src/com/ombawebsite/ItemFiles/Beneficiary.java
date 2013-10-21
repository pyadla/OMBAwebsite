package com.ombawebsite.ItemFiles;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Beneficiary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customerId;
	private String beneficiaryAccountNo;
	private String beneficiaryName;
	private String beneficiaryBankCode;
	
	public Beneficiary(String customerId,String beneficiaryAccountNo,
			String beneficiaryName,String beneficiaryBankCode) {
		this.customerId=customerId;
        this.beneficiaryAccountNo=beneficiaryAccountNo;
        this.beneficiaryName=beneficiaryName;
        this.beneficiaryBankCode=beneficiaryBankCode;
        
        
	}
		public Long getId() {
			return id;
		}
				
		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getBeneficiaryAccountNo() {
			return beneficiaryAccountNo;
		}

		public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
			this.beneficiaryAccountNo = beneficiaryAccountNo;
		}
		
		public String getBeneficiaryName() {
			return beneficiaryName;
		}

		public void setBeneficiaryName(String beneficiaryName) {
			this.beneficiaryName = beneficiaryName;
		}
		public String getBeneficiaryBankCode() {
			return beneficiaryBankCode;
		}

		public void setBeneficiaryBankCode(String beneficiaryBankCode) {
			this.beneficiaryBankCode = beneficiaryBankCode;
		}
		
	}
	



