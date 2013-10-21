package com.ombawebsite.DaoFiles;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ombawebsite.EMFService;
import com.ombawebsite.ItemFiles.SecurityQuestion;
import com.ombawebsite.ItemFiles.User;


public enum UserDao {
	  INSTANCE;

	@SuppressWarnings("unchecked")
	public List<User> listCustomers() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select from "+User.class.getName());
		List<User> customers = q.getResultList();
		return customers;
	}

	public void add(String customerId,String password,List<SecurityQuestion> sq) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			User customer = new User(customerId,password,sq);
			em.persist(customer);
			em.close();
		}
	}
	
	public void updateSQ(Long id,List<SecurityQuestion> SQ) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			User user = em.find(User.class, id);
			user.setSQ(SQ);
			em.persist(user);
			em.close();
		}
	}
	public void changePassword(Long id,String password) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			User user = em.find(User.class, id);
			user.setPassword(password);
			em.persist(user);
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getCustomer(String customerId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from User t where t.customerId = :customerId");
		q.setParameter("customerId", customerId);
		List<User> customer = q.getResultList();
		return customer;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			User customer = em.find(User.class, id);
			List<SecurityQuestion>sq=customer.getSQ();
			for(SecurityQuestion s:sq){
				em.remove(s);
			}
			em.remove(customer);
		} finally {
			em.close();
		}
	}

	
}


