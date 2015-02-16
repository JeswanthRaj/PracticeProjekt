package com.jms.projekt.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jms.projekt.dao.HibernateDao;
import com.jms.projekt.hibernate.model.Element;

@Repository
public class HibernateDaoImpl implements HibernateDao {

	@Autowired
	private SessionFactory session;
	
	public SessionFactory getSession() {
		return session;
	}



	public void setSession(SessionFactory session) {
		this.session = session;
	}



	public HibernateDaoImpl(){}
	
	

	@Override
	public void createElement(Element element) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(element);
		
	}

	@Override
	public void updateElement(Element element) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(element);
		
	}

	@Override
	public void deleteElement(int id) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getElement(id));
		
	}

	@Override
	public Element getElement(int elementId) {
		// TODO Auto-generated method stub
		System.out.println("HibernateDaoImpl.getElement()::"+elementId);
		return (Element)session.getCurrentSession().get(Element.class, elementId);
	}
	
	@Override
	public Element getElementByName(String name) {
		// TODO Auto-generated method stub
		System.out.println("HibernateDaoImpl.getElement()::"+name);
		String sql="from Element where elementName ='"+name+"'";
		Query query=session.getCurrentSession().createQuery(sql);
		return (Element)query.uniqueResult();
	}
	
	@Override
	public List<Element> getElementByRange(int from, int to) {
		System.out.println("HibernateDaoImpl.getElementByRange()::"+from+"::"+to);
		String sql="from Element where elementId between '"+from+"'and'"+to+"'";
		return session.getCurrentSession().createQuery(sql).list();
	}

	@Override
	public List<Element> getAllElements() {
		return session.getCurrentSession().createQuery("from Element").list();
	}

}
