package com.classified.controller;

import com.classified.model.Post;
import com.classified.service.PostService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zamuna on 6/18/2017.
 */
@Path("/post")
public class PostController {

    private PostService postService;

    public PostController() {
        postService = new PostService(Post.class);
    }

    @GET
    public Response getPosts() {
        List<Post> posts = new ArrayList<>(postService.getAll(null, null, null, null));
        System.out.println("Posts:" + posts);
        if (posts.size() > 0) {
            Response response = Response.ok(posts, MediaType.APPLICATION_JSON).build();
            return response;
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response getPosts(@PathParam("id") long id) {
        Post post = postService.getbyid(id);
        if (post != null) {
            return Response.ok(post, MediaType.APPLICATION_JSON).build();
        }
        return Response.noContent().build();
    }

    /*@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Post post) {
        System.out.println("Post");
        Long id = postService.insert(post);
        if (id != null && id > 0) {
            post.setId(id);
            return Response.status(201).entity(post).build();
        }
        return Response.status(400).entity("Error in creating post").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Post post) {
        Integer affected = postService.update(post, id, true);
        if (affected > 0) {
            post.setId(id);
            return Response.status(200).entity(post).build();
        }
        return Response.status(400).entity("Invalid Input for post").build();
    }*/

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        Long affected = postService.delete(id);
        if (affected > 0) {
            return Response.status(200).entity("Post deleted").build();
        }
        return Response.status(400).entity("Post cannot be deleted").build();
    }
}
