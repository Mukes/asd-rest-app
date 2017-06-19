package com.classified.controller;

import com.asd.framework.error.ErrorMessage;
import com.classified.model.Post;
import com.classified.service.PostService;
import com.sun.jersey.core.header.FormDataContentDisposition;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zamuna on 6/18/2017.
 */
@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
public class PostController {

    private PostService postService;

    public PostController() {
        postService = new PostService(Post.class);
    }

    @GET
    public Response getPosts(@QueryParam("search") String search,@QueryParam("limit") String limit,@QueryParam("offset") String offset) {
        List<Post> posts = new ArrayList<>(postService.getAll(search, null, offset, limit));
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Post post) {
        Object obj = postService.insert(post);
        List errors = null;
        if (obj instanceof Long){
            Long id = (Long) obj;
            if (id != null && id > 0) {
                post.setId(id);
                return Response.status(201).entity(post).build();
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
    public Response update(@PathParam("id") long id, Post post) {
        Object obj = postService.update(post, id, true);
        List errors = null;
        if (obj instanceof Integer){
            Integer id1 = (Integer) obj;
            if (id1 != null && id1 > 0) {
                post.setId(id);
                return Response.status(200).entity(post).build();
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
        Long affected = postService.delete(id);
        if (affected > 0) {
            return Response.status(200).entity("Post deleted").build();
        }
        return Response.status(400).entity("Post cannot be deleted").build();
    }

    /*@POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
                               @FormDataParam("file") FormDataContentDisposition fileDetail, Post post){

        System.out.println("Checking ");
        String uploadedFileLocation = System.getProperty("post.dir")+File.separator+"src"+File.separator+"main"+File.separator+"postImage"+File.separator+fileDetail.getName();
        System.out.println("New Location is: "+uploadedFileLocation);
        // save it
        writeToFile(uploadedInputStream, uploadedFileLocation);

        String output = "File uploaded to : " + uploadedFileLocation;

        Object obj = postService.insert(post);
        List errors = null;
        System.out.println("instance of obj:"+obj);
        if (obj instanceof Long){
            Long id = (Long) obj;
            if (id != null && id > 0) {
                post.setId(id);
                return Response.status(201).entity(post).build();
            }
        }else {
            if (obj instanceof List){
                errors = (ArrayList<ErrorMessage>) obj;
                System.out.println("error:"+errors);
            }
        }
        return Response.status(406).entity(errors).build();
    }*/

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream,
                             String uploadedFileLocation) {
        try {
            OutputStream out = new FileOutputStream(new File(
                    uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
