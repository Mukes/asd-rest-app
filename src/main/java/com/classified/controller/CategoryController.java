package com.classified.controller;

import com.classified.model.Category;
import com.classified.service.CategoryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zamuna on 6/17/2017.
 */
@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController() {
        categoryService = new CategoryService(Category.class);
    }

    @GET
    public Response getCategorys() {
        List<Category> categorys = new ArrayList<>(categoryService.getAll(null, null, null, null));
        System.out.println("Categorys:" + categorys);
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

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Category category) {
        Long id = categoryService.insert(category);
        if (id != null && id > 0) {
            category.setId(id);
            return Response.status(201).entity(category).build();
        }
        return Response.status(400).entity("Error in creating category").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Category category) {
        Integer affected = categoryService.update(category, id, true);
        if (affected > 0) {
            category.setId(id);
            return Response.status(200).entity(category).build();
        }
        return Response.status(400).entity("Invalid Input for category").build();
    }*/

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
