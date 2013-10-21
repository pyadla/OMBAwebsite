package com.ombawebsite.DaoFiles;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ombawebsite.ItemFiles.Customer;
import com.ombawebsite.*;

public enum CustomerDao {
	 INSTANCE;

	@SuppressWarnings("unchecked")
	public List<Customer> listCustomers() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Customer m");
		List<Customer> customers = q.getResultList();
		return customers;
	}

	public void add(String name,String dateOfBirth,String customerId,String mobileNo,
    		String permanentAddress,String currentAddress,String fathersName,String nationality,String sex,
    		String occupation,String annualIncome,String emailAddress) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Customer customer = new Customer(name,dateOfBirth,customerId,
					mobileNo,permanentAddress,currentAddress,fathersName,nationality,sex,occupation,annualIncome,
					emailAddress);
			em.persist(customer);
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getCustomer(String customerId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Customer t where t.customerId = :customerId");
		q.setParameter("customerId", customerId);
		List<Customer> customer = q.getResultList();
		return customer;
	}
	
	public void updateDetails(Long id,String ph,String ad,String email) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Customer customer = em.find(Customer.class, id);
			customer.setMobileNo(ph);
			customer.setCurrentAddress(ad);
			customer.setEmailAddress(email);
			em.persist(customer);
			em.close();
		}
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Customer customer = em.find(Customer.class, id);
			em.remove(customer);
		} finally {
			em.close();
		}
	}
}

