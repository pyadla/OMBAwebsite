package com.ombawebsite.DaoFiles;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ombawebsite.EMFService;
import com.ombawebsite.ItemFiles.Beneficiary;

public enum BeneficiaryDao {
	  INSTANCE;

	@SuppressWarnings("unchecked")
	public List<Beneficiary> listBeneficiaries() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Beneficiary m");
		List<Beneficiary> beneficiaries = q.getResultList();
		return beneficiaries;
	}

	public void add(String customerId,String beneficiaryAccountNo,
			String beneficiaryName,String beneficiaryBankCode) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Beneficiary beneficiary = new Beneficiary(customerId,beneficiaryAccountNo,
					beneficiaryName,beneficiaryBankCode);
			em.persist(beneficiary);
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Beneficiary> getBeneficiaries(String customerId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Beneficiary t where t.customerId = :customerId");
		q.setParameter("customerId", customerId);
		List<Beneficiary> beneficiaries = q.getResultList();
		return beneficiaries;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Beneficiary beneficiary = em.find(Beneficiary.class, id);
			em.remove(beneficiary);
		} finally {
			em.close();
		}
	}
}
