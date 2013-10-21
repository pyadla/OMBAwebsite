package com.ombawebsite.DaoFiles;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ombawebsite.EMFService;
import com.ombawebsite.ItemFiles.Account;
  

	public enum AccountDao {
		INSTANCE;

		@SuppressWarnings("unchecked")
		public List<Account> listAccounts() {
			EntityManager em = EMFService.get().createEntityManager();
			// Read the existing entries
			Query q = em.createQuery("select m from Account m");
			List<Account> accounts = q.getResultList();
			return accounts;
		}

		public void add(String accountNo,String customerId,String balance,String lastStatementDate,
				String lastTransactionDate,String creationDate) {
			synchronized (this) {
				EntityManager em = EMFService.get().createEntityManager();
				Account account = new Account(accountNo,customerId,balance,
						lastStatementDate,lastTransactionDate,creationDate);
				em.persist(account);
				em.close();
			}
		}
		
			@SuppressWarnings("unchecked")
			public List<Account> getAccounts(String customerId) {
				EntityManager em = EMFService.get().createEntityManager();
				Query q = em.createQuery("select t from Account t where t.customerId = :customerId");
				q.setParameter("customerId", customerId);
				List<Account> accounts = q.getResultList();
				return accounts;
			}
			
			public void updateBalance(Long id,String balance) {
				synchronized (this) {
					EntityManager em = EMFService.get().createEntityManager();
					Account account = em.find(Account.class, id);
					account.setBalance(balance);
					em.persist(account);
					em.close();
				}
			}
			
			public void updateTransactionDate(Long id,String lastTransactionDate) {
				synchronized (this) {
					EntityManager em = EMFService.get().createEntityManager();
					Account account = em.find(Account.class, id);
					account.setLastTransactionDate(lastTransactionDate);
					em.persist(account);
					em.close();
				}
			}
			
			public void updateStatementDate(Long id,String lastStatementDate) {
				synchronized (this) {
					EntityManager em = EMFService.get().createEntityManager();
					Account account = em.find(Account.class, id);
					account.setLastStatementDate(lastStatementDate);
					em.persist(account);
					em.close();
				}
			}
			
			public void remove(long id) {
				EntityManager em = EMFService.get().createEntityManager();
				try {
					 Account account = em.find(Account.class, id);
					em.remove(account);
				} finally {
					em.close();
				}
			}
		}
		

		
			
		

		
	

				




