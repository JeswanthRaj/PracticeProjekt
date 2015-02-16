package com.jms.projekt.hibernate.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Element {
	
	@Id
	@Column(name="ELEMENT_ID")
	private int elementId;
	@Column(name="ELEMENT_NAME")
	private String elementName;
	@Column(name="ELEMENT_TYPE")
	private String elementType;
	@Column(name="ELEMENT_VALUE")
	private String elementValue;
	@Column(name="LUD")
	private Date lud;
	
	public Element(){}
	
	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getElementValue() {
		return elementValue;
	}

	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

	public Date getLud() {
		return lud;
	}

	public void setLud(Date lud) {
		this.lud = lud;
	}
}
