package com.jms.projekt.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jms.projekt.dao.JdbcDao;
import com.jms.projekt.model.Element;
import com.jms.projekt.utility.JSONUtility;

@Path("element")
public class ProjektServiceJdbcBean {
	
	// /////HTTP CRUD Operations/////

	// /////GET-NoParam///////
	@GET
	@Path("/all")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getAllElements() {
		Connection con = null;
		PreparedStatement ps = null;
		JSONArray jArray = new JSONArray();
		String jsonResponse = null;
		try {
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			DataSource ds = (DataSource)context.getBean("dataSource");
			con=ds.getConnection();
			String sql = "SELECT * FROM ELEMENT";
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (null != rs) {
				JSONUtility jsonUtility = new JSONUtility();
				jArray = jsonUtility.toJSONArray(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if (null != ps && !ps.isClosed())
					ps.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != jArray)
			jsonResponse = jArray.toString();

		return jsonResponse;
	}

	// /////GET-PathParam///////
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getElement(@PathParam("id") int id) {
		Connection con = null;
		PreparedStatement ps = null;
		JSONArray jArray = new JSONArray();
		String jsonResponse = null;
		try {
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			DataSource ds = (DataSource)context.getBean("dataSource");
			con=ds.getConnection();
			String sql = "SELECT * FROM ELEMENT WHERE ELEMENT_ID=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (null != rs) {
				JSONUtility jsonUtility = new JSONUtility();
				jArray = jsonUtility.toJSONArray(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ps && !ps.isClosed())
					ps.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != jArray)
			jsonResponse = jArray.toString();

		return jsonResponse;
	}

	// /////GET-QueryParam///////
	@GET
	@Path("/byRange")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getElementByRange(
			@DefaultValue("1") @QueryParam("from") int from,
			@DefaultValue("2") @QueryParam("to") int to) {
		System.out.println("from::" + from + "to::" + to);
		Connection con = null;
		PreparedStatement ps = null;
		JSONArray jArray = new JSONArray();
		String jsonResponse = null;
		try {
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			DataSource ds = (DataSource)context.getBean("dataSource");
			con=ds.getConnection();
			String sql = "SELECT * FROM ELEMENT WHERE ELEMENT_ID BETWEEN ? AND ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, from);
			ps.setInt(2, to);
			ResultSet rs = ps.executeQuery();

			if (null != rs) {
				JSONUtility jsonUtility = new JSONUtility();
				jArray = jsonUtility.toJSONArray(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ps && !ps.isClosed())
					ps.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != jArray)
			jsonResponse = jArray.toString();

		return jsonResponse;
	}

	// /////GET-QueryParam///////
	@GET
	@Path("/byName")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getElementByName(
			@DefaultValue("GOLD") @QueryParam("name") String name) {
		
		Connection con = null;
		PreparedStatement ps = null;
		JSONArray jArray = new JSONArray();
		String jsonResponse = null;
		try {
			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			DataSource ds = (DataSource)context.getBean("dataSource");
			con=ds.getConnection();
			String sql = "SELECT * FROM ELEMENT WHERE ELEMENT_NAME = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (null != rs) {
				JSONUtility jsonUtility = new JSONUtility();
				jArray = jsonUtility.toJSONArray(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != ps && !ps.isClosed())
					ps.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (null != jArray)
			jsonResponse = jArray.toString();

		return jsonResponse;
	}

	// //POST-Insert////
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/add")
	public Response addElement(String request) {
		System.out.println("Element Add!" + request);
		ObjectMapper jObj = new ObjectMapper();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Element element = jObj.readValue(request, Element.class);

			ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
			DataSource ds = (DataSource)context.getBean("dataSource");
			con=ds.getConnection();
			
			String sql = "INSERT INTO ELEMENT (ELEMENT_ID,ELEMENT_NAME,ELEMENT_TYPE,ELEMENT_VALUE,LUD) VALUES (?,?,?,?,CURRENT_TIMESTAMP)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, element.getElementId());
			ps.setString(2, element.getElementName());
			ps.setString(3, element.getElementType());
			ps.setString(4, element.getElementValue());
			ps.executeUpdate();

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).entity(e.getMessage()).build();
		}
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
			Connection con = null;
			PreparedStatement ps = null;
			try {
				Element element = jObj.readValue(request, Element.class);

				ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
				DataSource ds = (DataSource)context.getBean("dataSource");
				con=ds.getConnection();
				
			   String sql="SELECT * FROM ELEMENT WHERE ELEMENT_ID=?";
			   ps=con.prepareStatement(sql);
			   ps.setInt(1, element.getElementId());
				ResultSet rs=ps.executeQuery();
				if(null!=rs){
				ps.clearBatch();
				sql = "UPDATE ELEMENT SET ELEMENT_NAME=?,ELEMENT_TYPE=?, ELEMENT_VALUE=?, LUD=CURRENT_TIMESTAMP WHERE ELEMENT_ID=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, element.getElementName());
				ps.setString(2, element.getElementType());
				ps.setString(3, element.getElementValue());
				ps.setInt(4, element.getElementId());
				ps.executeUpdate();
				}
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(500).entity(e.getMessage()).build();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(500).entity(e.getMessage()).build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(500).entity(e.getMessage()).build();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(500).entity(e.getMessage()).build();
			}
			return Response.status(200).entity("Update Successfull!!!").build();
		}
}
