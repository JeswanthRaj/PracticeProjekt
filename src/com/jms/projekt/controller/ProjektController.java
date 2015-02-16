package com.jms.projekt.controller;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.jms.projekt.hibernate.model.Element;
import com.jms.projekt.service.ProjektService;

@Path("/helement")
@Controller
public class ProjektController {
	
	public ProjektController(){}
	
	@Autowired
	private ProjektService projektService;
	


	public ProjektService getProjektService() {
		return projektService;
	}



	public void setProjektService(ProjektService projektService) {
		this.projektService = projektService;
	}



	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Element getElementById(@PathParam("id") int id){
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		projektService=context.getBean("projektServiceImpl",ProjektService.class);
		System.out.println("ProjektController.getElementById::"+id+"Obj::"+projektService);
		Element element=projektService.getElement(id);
		return element;
	}
	
	@GET
	@Path("/name/{name}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Element getElementByName(@PathParam("name") String name){
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		projektService=context.getBean("projektServiceImpl",ProjektService.class);
		System.out.println("ProjektController.getElementByName::"+name+"Obj::"+projektService);
		Element element=projektService.getElementByName(name);
		return element;
	}
	
	@GET
	@Path("/byRange")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List getElementByRange(
			@QueryParam("from") int from,
			@QueryParam("to") int to){
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		projektService=context.getBean("projektServiceImpl",ProjektService.class);
		System.out.println("from::"+from+"to::"+to);
		List<Element> elements=projektService.getElementByRange(from, to);
		return elements;
	}
	
	@GET
	@Path("/all")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Element> getAllElement(){
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		projektService=context.getBean("projektServiceImpl",ProjektService.class);
		System.out.println("ProjektController.getAllElements::"+projektService);
		List<Element> elements=projektService.getAllElements();
		return elements;
	}
	
	@POST
	@Path("/add")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response addElement(String request){
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		projektService=context.getBean("projektServiceImpl",ProjektService.class);
		ObjectMapper mapper=new ObjectMapper();
		Element element=null;
		try {
			element = mapper.readValue(request, Element.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Request::"+request);
		projektService.createElement(element);
		return Response.status(200).entity("OK").build();
	}
	
	@PUT
	@Path("/update")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response updateElement(String request){
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		projektService=context.getBean("projektServiceImpl",ProjektService.class);
		ObjectMapper mapper=new ObjectMapper();
		Element element=null;
		try {
			element = mapper.readValue(request, Element.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Request::"+request);
		projektService.updateElement(element);
		return Response.status(200).entity("OK").build();
	}
	
	@DELETE
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response deleteElementById(@PathParam("id") int id){
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		projektService=context.getBean("projektServiceImpl",ProjektService.class);
		System.out.println("ProjektController.getElementById::"+id+"Obj::"+projektService);
		projektService.deleteElement(id);
		return Response.status(200).entity("Element Deleted!!").build();
	}
}
