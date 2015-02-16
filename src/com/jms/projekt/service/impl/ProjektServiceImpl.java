package com.jms.projekt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.jms.projekt.dao.HibernateDao;
import com.jms.projekt.hibernate.model.Element;
import com.jms.projekt.service.ProjektService;
@Service
@EnableTransactionManagement
public class ProjektServiceImpl implements ProjektService{

	@Autowired
	private HibernateDao hibernateDao;
	public ProjektServiceImpl(){}
	
	public HibernateDao getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(HibernateDao hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	@Transactional
	public void createElement(Element element) {
		hibernateDao.createElement(element);
		
	}

	@Transactional
	public void updateElement(Element element) {
		hibernateDao.updateElement(element);		
	}

	@Transactional
	public void deleteElement(int id) {
		hibernateDao.deleteElement(id);		
	}

	@Transactional
	public Element getElement(int id) {
		return hibernateDao.getElement(id);
	}
	
	@Transactional
	public Element getElementByName(String name) {
		return hibernateDao.getElementByName(name);
	}
	
	@Transactional
	public List getElementByRange(int from,int to) {
		return hibernateDao.getElementByRange(from,to);
	}

	@Transactional
	public List<Element> getAllElements() {
		// TODO Auto-generated method stub
		return hibernateDao.getAllElements();
	}

}
