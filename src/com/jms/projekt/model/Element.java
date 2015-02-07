package com.jms.projekt.model;

import java.sql.Date;

public class Element {

	public Element(){}
	private int elementId;
	private String elementName;
	private String elementType;
	private String elementValue;
	
	private Date lud;
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + elementId;
		result = prime * result
				+ ((elementName == null) ? 0 : elementName.hashCode());
		result = prime * result
				+ ((elementType == null) ? 0 : elementType.hashCode());
		result = prime * result
				+ ((elementValue == null) ? 0 : elementValue.hashCode());
		result = prime * result + ((lud == null) ? 0 : lud.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Element other = (Element) obj;
		if (elementId != other.elementId)
			return false;
		if (elementName == null) {
			if (other.elementName != null)
				return false;
		} else if (!elementName.equals(other.elementName))
			return false;
		if (elementType == null) {
			if (other.elementType != null)
				return false;
		} else if (!elementType.equals(other.elementType))
			return false;
		if (elementValue == null) {
			if (other.elementValue != null)
				return false;
		} else if (!elementValue.equals(other.elementValue))
			return false;
		if (lud == null) {
			if (other.lud != null)
				return false;
		} else if (!lud.equals(other.lud))
			return false;
		return true;
	}
}
