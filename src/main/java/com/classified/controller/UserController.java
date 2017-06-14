package com.classified.controller;

import com.classified.model.User;
import com.classified.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crawlers on 6/13/2017.
 */
@Path("/user")
public class UserController {

    private UserService userService;
    public UserController(){
        userService = new UserService(User.class);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts(){
        List<User> accounts = new ArrayList<>(userService.getAll(null, null, null, null));
        if (accounts.size()>0){
            return Response.ok(accounts, MediaType.APPLICATION_JSON).build();
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccounts(@PathParam("id") long id){
        User account = userService.getbyid(id);
        if (account==null){
            return Response.ok(account, MediaType.APPLICATION_JSON).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        Long id = userService.insert(user);
        if (id!=null && id<0){
            user.setId((int)(long)id);
            return Response.status(201).entity(user).build();
        }
        return Response.status(400).entity("Error in creating user").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, User user ) {
            Integer affected = userService.update(user, id, true);
        if (affected>0){
            return Response.status(200).entity("User updated").build();
        }
        return Response.status(400).entity("Invalid Input for user").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        Long affected = userService.delete(id);
        if (affected>0){
            return Response.status(200).entity("User deleted").build();
        }
        return Response.status(400).entity("User cannot be deleted").build();
    }
}
