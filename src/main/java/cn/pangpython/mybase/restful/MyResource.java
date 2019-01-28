package cn.pangpython.mybase.restful;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/my")
@Produces(MediaType.TEXT_PLAIN)
public class MyResource {
    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_HTML)
    public String hello(){
        return "hello !!!";
    }

    @PUT
    @Path("/add/{id}")
    public Response add(@PathParam("id") int id){
        return Response.status(200)
                .entity("response_code 200, ok!"+id)
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
