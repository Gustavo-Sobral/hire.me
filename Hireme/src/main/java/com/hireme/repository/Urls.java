package com.hireme.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.hireme.model.Url;
import com.hireme.util.jpa.Transactional;

public class Urls implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Url guardar(Url url) {
		return manager.merge(url);
	}
	
	public Long ultimoId() {
		Session session = manager.unwrap(Session.class);
		Criteria c = session.createCriteria(Url.class);  
		c.setProjection(Projections.max("id"));  
		return (Long) c.uniqueResult();		
	}
	
	public Url porUrlCurta(String urlCurta){
		try {
			return manager.createQuery("from Url where (urlCurta) = :urlCurta", Url.class)
				.setParameter("urlCurta", urlCurta)
				.getSingleResult();
		} catch (NoResultException e) {
			return null;			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Url> maisAcessadas(){
		Session session = manager.unwrap(Session.class);
		Criteria c = session.createCriteria(Url.class);	
		
		return c.addOrder(Order.desc("visitas")).setMaxResults(10).list();
	}
	
	@Transactional
	public Url visitar(Url url) {
		int visitas = url.getVisitas();
		url.setVisitas(visitas+1);
		return manager.merge(url);
	}
}