package com.ombawebsite.DaoFiles;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ombawebsite.EMFService;
import com.ombawebsite.ItemFiles.ScheduleTransaction;
  

	public enum SchedTransDao {
		INSTANCE;

		@SuppressWarnings("unchecked")
		public List<ScheduleTransaction> listAccounts() {
			EntityManager em = EMFService.get().createEntityManager();
			// Read the existing entries
			Query q = em.createQuery("select m from ScheduleTransaction m");
			List<ScheduleTransaction> accounts = q.getResultList();
			return accounts;
		}

		public void add(String customerId,String fromaccount,String toaccount,String amount,String date) {
			synchronized (this) {
				EntityManager em = EMFService.get().createEntityManager();
				ScheduleTransaction account = new ScheduleTransaction(customerId,fromaccount,toaccount,amount,date);
				em.persist(account);
				em.close();
			}
		}
		
			
			public void updateBalance(Long id,String date) {
				synchronized (this) {
					EntityManager em = EMFService.get().createEntityManager();
					ScheduleTransaction account = em.find(ScheduleTransaction.class, id);
					account.setDate(date);
					em.persist(account);
					em.close();
				}
		}
}