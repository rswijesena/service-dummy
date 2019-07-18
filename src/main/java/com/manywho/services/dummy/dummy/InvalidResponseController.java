package com.manywho.services.dummy.dummy;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;

@Path("/invalidresponse")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvalidResponseController {
    
    @Path("/invaliddate")
    @POST
    public Response invalid() throws UnsupportedEncodingException {

        return Response.ok("{\"annotations\":{},\"culture\":null,\"invokeType\":\"FORWARD\",\"mode\":null,\"outputs\":[{\"contentType\":\"ContentDateTime\",\"contentValue\":\"NotAValidDate\",\"developerName\":\"Body\",\"objectData\":[],\"typeElementDeveloperName\":null,\"typeElementId\":null,\"typeElementPropertyDeveloperName\":null,\"typeElementPropertyId\":null,\"valueElementId\":null}],\"rootFaults\":{},\"selectedOutcomeId\":null,\"tenantId\":\"65e49be8-c8b4-4b5d-b2d2-a52e95ccc307\",\"token\":\"d07b2784-bf8a-4879-8b22-6f32bb482876\",\"valueFaults\":[],\"waitMessage\":null}").build();
    }
}
