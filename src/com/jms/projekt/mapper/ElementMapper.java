package com.jms.projekt.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.jms.projekt.model.Element;

public class ElementMapper implements RowMapper<Element>{

	public List<Element> mapRow(List<Map<String, Object>> elements) {
		List<Element> elementList=new ArrayList<Element>();
		for(Map<String, Object> rows:elements){
			Element element=new Element();
			System.out.println(Integer.parseInt(rows.get("ELEMENT_ID").toString()));
			element.setElementId(Integer.parseInt(rows.get("ELEMENT_ID").toString()));
			element.setElementName(rows.get("ELEMENT_NAME").toString());
			element.setElementType(rows.get("ELEMENT_TYPE").toString());
			element.setElementValue(rows.get("ELEMENT_VALUE").toString());
			System.out.println("rows.get(LUD)::"+rows.get("LUD").toString());
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
			Date date=null;
			try {
				date = new Date((df.parse(rows.get("LUD").toString()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			element.setLud(date);
			
			elementList.add(element);
		}
		// TODO Auto-generated method stub
		return elementList;
	}

	@Override
	public Element mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		Element element=new Element();
		
		element.setElementId(rs.getInt(1));
		element.setElementName(rs.getString(2));
		element.setElementType(rs.getString(3));
		element.setElementValue(rs.getString(4));
		element.setLud(rs.getDate(5));
		
		return element;
	}

}
