package com.jms.projekt.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jms.projekt.exception.NoElementFoundException;
import com.jms.projekt.mapper.ElementMapper;
import com.jms.projekt.model.Element;
import com.jms.projekt.sql.SQLConstants;

@Path("element")
public class ProjektServiceJdbcBean {
	
	// /////HTTP CRUD Operations/////

	// /////GET-NoParam///////
	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Element> getAllElements() {
		List<Map<String, Object>> elements=null;
		List<Element> elementList=new ArrayList<Element>();

		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");

			JdbcTemplate jdbcTemplate=context.getBean("jdbcTemplate",JdbcTemplate.class);
			
			String sql = SQLConstants.GET_ALL_ELEMENTS;
			elements=jdbcTemplate.queryForList(sql);
			
			ElementMapper eMapper=new ElementMapper();
			elementList=eMapper.mapRow(elements);
			
		return elementList;
	}

	// /////GET-PathParam///////
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Element getElement(@PathParam("id") int id) {
		
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate",JdbcTemplate.class);
			String sql = SQLConstants.GET_ELEMENT_BY_ID;
			Element element = jdbcTemplate.queryForObject(sql,new Object[] {id},new ElementMapper());
			
		return element;
	}

	// /////GET-QueryParam///////
	@GET
	@Path("/byRange")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Element> getElementByRange(
			@DefaultValue("1") @QueryParam("from") int from,
			@DefaultValue("2") @QueryParam("to") int to) {
		System.out.println("from::" + from + "to::" + to);
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			JdbcTemplate jdbcTemplate=context.getBean("jdbcTemplate",JdbcTemplate.class);
			String sql = SQLConstants.GET_ELEMENT_BY_RANGE;

			List<Map<String,Object>> elements = jdbcTemplate.queryForList(sql,new Object[]{from,to});
			ElementMapper eMapper=new ElementMapper();
			List<Element> elementList=eMapper.mapRow(elements);

		return elementList;
	}

	// /////GET-QueryParam///////
	@GET
	@Path("/byName")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Element> getElementByName(
			@DefaultValue("GOLD") @QueryParam("name") String name) {
		
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			JdbcTemplate jdbcTemplate=context.getBean("jdbcTemplate",JdbcTemplate.class);
			String sql = SQLConstants.GET_ELEMENT_BY_NAME;
			
			List<Map<String,Object>> elements = jdbcTemplate.queryForList(sql,new Object[]{name});
			ElementMapper eMapper=new ElementMapper();
			List<Element> elementList=eMapper.mapRow(elements);
		return elementList;
	}

	// //POST-Insert////
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/add")
	public Response addElement(String request) {
		System.out.println("Element Add!" + request);
		ObjectMapper jObj = new ObjectMapper();
		Element element=null;
			try {
				element = jObj.readValue(request, Element.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate",JdbcTemplate.class);
			
			String sql = SQLConstants.ADD_ELEMENT;
			jdbcTemplate.update(sql, new Object[]{
					element.getElementId(),
					element.getElementName(),
					element.getElementType(),
					element.getElementValue()});

		return Response.status(200).entity("Insert Successfull!!!").build();
	}
	
	// //PUT-Update////
		@PUT
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		@Path("/update")
		public Response updateElement(String request) {
			System.out.println("Element Update!" + request);
			ObjectMapper jObj = new ObjectMapper();
				Element element=null;
				try {
					element = jObj.readValue(request, Element.class);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
				JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate",JdbcTemplate.class);
				
			   String sql=SQLConstants.GET_ELEMENT_BY_ID;
			   List<Map<String,Object>> rows=jdbcTemplate.queryForList(sql,new Object[]{element.getElementId()});	
				if(null!=rows && !rows.isEmpty()){
				sql = SQLConstants.UPDATE_ELEMENT;
				jdbcTemplate.update(sql,new Object[]{element.getElementName(),element.getElementType(),element.getElementValue(),element.getElementId()});
				}
				
			return Response.status(200).entity("Update Successfull!!!").build();
		}
		
		
		// ////DELETE-Delete/////
		@DELETE
		@Path("{id}")
		@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
		public Response deleteElementById(@PathParam("id") int id) throws NoElementFoundException{
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			JdbcTemplate jdbcTemplate=context.getBean("jdbcTemplate",JdbcTemplate.class);
			String sql=SQLConstants.DELETE_ELEMENT;
			jdbcTemplate.update(sql,new Object[]{id});
	
			return Response.status(200).entity("Element Deleted!!!").build();
		}
}
