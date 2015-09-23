package com.previsaotempo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.previsaotempo.CidadesPrevisaoTempo;

public class CidadesPrevisaoTempoDao {
	protected EntityManager entityManager;

	public CidadesPrevisaoTempoDao() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PrevisaoTempo");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public CidadesPrevisaoTempo getById(final Long id) {
		return entityManager.find(CidadesPrevisaoTempo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<CidadesPrevisaoTempo> findAll() {
		return entityManager.createQuery("FROM " + CidadesPrevisaoTempo.class.getName()).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CidadesPrevisaoTempo> findByCountry(String country) {
		return entityManager.createQuery("FROM " + CidadesPrevisaoTempo.class.getName() + " WHERE PAIS = ?").setParameter(1, country).getResultList();
	}

	public void persist(CidadesPrevisaoTempo cidade) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cidade);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(CidadesPrevisaoTempo cidade) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cidade);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void close() {
		entityManager.close();
	}
}
