package com.classified.controller;

import com.asd.framework.error.ErrorMessage;
import com.classified.model.Category;
import com.classified.service.CategoryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController() {
        categoryService = new CategoryService(Category.class);
    }

    @GET
    public Response getCategorys(@QueryParam("search") String search,@QueryParam("limit") String limit,@QueryParam("offset") String offset) {
        List<Category> categorys = new ArrayList<>(categoryService.getAll(search, null, offset, limit));
        if (categorys.size() > 0) {
            Response response = Response.ok(categorys, MediaType.APPLICATION_JSON).build();
            return response;
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response getCategorys(@PathParam("id") long id) {
        Category category = categoryService.getbyid(id);
        if (category != null) {
            return Response.ok(category, MediaType.APPLICATION_JSON).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Category category) {
        Object obj = categoryService.insert(category);
        List errors = null;
        if (obj instanceof Long){
            Long id = (Long) obj;
            if (id != null && id > 0) {
                category.setId(id);
                return Response.status(201).entity(category).build();
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
    public Response update(@PathParam("id") long id, Category category) {
        Object obj = categoryService.update(category, id, true);
        List errors = null;
        if (obj instanceof Integer){
            Integer id1 = (Integer) obj;
            if (id1 != null && id1 > 0) {
                category.setId(id);
                return Response.status(200).entity(category).build();
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
        Long affected = categoryService.delete(id);
        if (affected > 0) {
            return Response.status(200).entity("Category deleted").build();
        }
        return Response.status(400).entity("Category cannot be deleted").build();
    }

    public static void main(String[] args) {
        CategoryController categoryController = new CategoryController();
        categoryController.delete(1l);
    }
}
