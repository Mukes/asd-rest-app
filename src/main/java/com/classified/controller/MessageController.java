package com.classified.controller;

import com.asd.framework.error.ErrorMessage;
import com.classified.model.Message;
import com.classified.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zamuna on 6/18/2017.
 */
@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
public class MessageController {
    private MessageService messageService;

    public MessageController() {
        messageService = new MessageService(Message.class);
    }

    @GET
    public Response getMessages(@QueryParam("search") String search,@QueryParam("limit") String limit,@QueryParam("offset") String offset) {
        List<Message> messages = new ArrayList<>(messageService.getAll(search, null, offset, limit));
        if (messages.size() > 0) {
            Response response = Response.ok(messages, MediaType.APPLICATION_JSON).build();
            return response;
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response getMessages(@PathParam("id") long id) {
        Message message = messageService.getbyid(id);
        if (message != null) {
            return Response.ok(message, MediaType.APPLICATION_JSON).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Message message) {
        Object obj = messageService.insert(message);
        List errors = null;
        if (obj instanceof Long){
            Long id = (Long) obj;
            if (id != null && id > 0) {
                message.setId(id);
                return Response.status(201).entity(message).build();
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
    public Response update(@PathParam("id") long id, Message message) {
        Object obj = messageService.update(message, id, true);
        List errors = null;

        if (obj instanceof Long){
            Long id1 = (Long) obj;
            if (id1 != null && id1 > 0) {
                message.setId(id);
                return Response.status(200).entity(message).build();
            }
        }else {

            if (obj instanceof List){
                errors = (ArrayList<ErrorMessage>) obj;
            }
        }
        return Response.status(406).entity(errors).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        Long affected = messageService.delete(id);
        if (affected > 0) {
            return Response.status(200).entity("Message deleted").build();
        }
        return Response.status(400).entity("Message cannot be deleted").build();
    }


}

