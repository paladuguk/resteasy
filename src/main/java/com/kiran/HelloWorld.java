package com.kiran;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.kiran.modal.User;
 

@Path("/helloWorld")
public class HelloWorld {    
    
	@GET
    @Produces(MediaType.APPLICATION_XML)
    public User helloName() {
       return new User(1,"Kiran");
    }
	
	@GET
	@Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User helloId(@PathParam("id") int id) {
		return new User(2,"Nani");
    }
}