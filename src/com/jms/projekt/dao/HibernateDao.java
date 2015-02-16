package com.jms.projekt.dao;

import java.util.List;

import com.jms.projekt.hibernate.model.Element;

public interface HibernateDao {

	public void createElement(Element element);
	public void updateElement(Element element);
	public void deleteElement(int id);
	public Element getElement(int id);
	public Element getElementByName(String name);
	public List<Element> getElementByRange(int from, int to);
	public List<Element> getAllElements();

}
