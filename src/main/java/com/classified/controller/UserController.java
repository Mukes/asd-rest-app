package com.classified.controller;

import com.asd.framework.error.ErrorMessage;
import com.classified.model.User;
import com.classified.model.UserWithToken;
import com.classified.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private UserService userService;

    public UserController() {
        userService = new UserService(User.class);
    }

    @GET
    public Response getUsers(@QueryParam("search") String search,@QueryParam("limit") String limit,@QueryParam("offset") String offset) {
        List<User> users = new ArrayList<>(userService.getAll(search, null, offset, limit));
        if (users.size() > 0) {
            Response response = Response.ok(users, MediaType.APPLICATION_JSON).build();
            return response;
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response getUsers(@PathParam("id") long id) {
        User user = userService.getbyid(id);
        if (user != null) {
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        Object obj = userService.insert(user);
        List errors = null;
        if (obj instanceof Long){
            Long id = (Long) obj;
            if (id != null && id > 0) {
                user.setId(id);
                return Response.status(201).entity(user).build();
            }
        }else {
            if (obj instanceof List){
                errors = (ArrayList<ErrorMessage>) obj;
            }
        }
        return Response.status(406).entity(errors).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, User user) {
        Object obj = userService.update(user, id, true);
        List errors = null;
        if (obj instanceof Integer){
            Integer id1 = (Integer) obj;
            if (id1 != null && id1 > 0) {
                user.setId(id);
                return Response.status(200).entity(user).build();
            }
        }else {

            if (obj instanceof List){
                errors = (ArrayList<ErrorMessage>) obj;
                System.out.println(errors);
            }
        }
        return Response.status(406).entity(errors).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        Long affected = userService.delete(id);
        if (affected > 0) {
            return Response.status(200).entity("User deleted").build();
        }
        return Response.status(400).entity("User cannot be deleted").build();
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        UserWithToken userWithToken = userService.login(user.getEmail(), user.getPassword());
        if (userWithToken != null) {
            return Response.status(200).entity(userWithToken).build();
        }
        return Response.status(400).entity("User cannot be deleted").build();
    }
}
