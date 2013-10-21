package com.ombawebsite.DaoFiles;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ombawebsite.EMFService;
import com.ombawebsite.ItemFiles.Transaction;

public enum TransactionDao {
	  INSTANCE;

	@SuppressWarnings("unchecked")
	public List<Transaction> listTransactions() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Transaction m");
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}

	public void add(String customerId,String accountNo,String dateOfTransaction,
			String timeOfTransaction,String toAccount,String amount,String balance,
			String toAccBalance,String description) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Transaction transaction = new Transaction(customerId,accountNo,dateOfTransaction,
					timeOfTransaction,toAccount,amount,balance,toAccBalance,description);
			em.persist(transaction);
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Transaction> getTransactionObjects(String customerId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Transaction t where t.customerId = :customerId");
		q.setParameter("customerId", customerId);
		List<Transaction> transactions = q.getResultList();
		return transactions;
	}
	
	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Transaction transaction = em.find(Transaction.class, id);
			em.remove(transaction);
		} finally {
			em.close();
		}
	}
}
