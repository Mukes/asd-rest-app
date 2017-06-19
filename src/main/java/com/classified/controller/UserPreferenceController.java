package com.classified.controller;

import com.asd.framework.error.ErrorMessage;
import com.asd.framework.error.ErrorMessage;
import com.classified.model.Message;
import com.classified.model.User;
import com.classified.model.UserPreference;
import com.classified.model.UserWithToken;
import com.classified.service.UserPreferenceService;
import com.classified.service.UserPreferenceService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zamuna on 6/18/2017.
 */
@Path("/userPreference")
@Produces(MediaType.APPLICATION_JSON)
public class UserPreferenceController {
    private UserPreferenceService userPreferenceService;

    public UserPreferenceController() {
        userPreferenceService = new UserPreferenceService(UserPreference.class);
    }

    @GET
    public Response getUserPreferences(@QueryParam("search") String search,@QueryParam("limit") String limit,@QueryParam("offset") String offset) {
        List<UserPreference> userPreferences = new ArrayList<>(userPreferenceService.getAll(search, null, offset, limit));
        if (userPreferences.size() > 0) {
            Response response = Response.ok(userPreferences, MediaType.APPLICATION_JSON).build();
            return response;
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response getUserPreferences(@PathParam("id") long id) {
        UserPreference userPreference = userPreferenceService.getbyid(id);
        if (userPreference != null) {
            return Response.ok(userPreference, MediaType.APPLICATION_JSON).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(UserPreference userPreference) {
        Object obj = userPreferenceService.insert(userPreference);
        List errors = null;
        if (obj instanceof Long){
            Long id = (Long) obj;
            if (id != null && id > 0) {
                userPreference.setId(id);
                return Response.status(201).entity(userPreference).build();
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
    public Response update(@PathParam("id") long id, UserPreference userPreference) {
        Object obj = userPreferenceService.update(userPreference, id, true);
        List errors = null;

        if (obj instanceof Long){
            Long id1 = (Long) obj;
            if (id1 != null && id1 > 0) {
                userPreference.setId(id);
                return Response.status(200).entity(userPreference).build();
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
        Long affected = userPreferenceService.delete(id);
        if (affected > 0) {
            return Response.status(200).entity("UserPreference deleted").build();
        }
        return Response.status(400).entity("UserPreference cannot be deleted").build();
    }


}