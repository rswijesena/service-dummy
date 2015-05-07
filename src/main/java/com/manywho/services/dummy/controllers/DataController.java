package com.manywho.services.dummy.controllers;

import com.manywho.sdk.entities.run.ApiProblemException;
import com.manywho.sdk.entities.run.ServiceProblemException;
import com.manywho.sdk.entities.run.elements.type.ObjectDataRequest;
import com.manywho.sdk.entities.run.elements.type.ObjectDataResponse;
import com.manywho.sdk.enums.InvokeType;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class DataController {

    @Context
    private HttpHeaders headers;

    @Path("/data")
    @POST
    public ObjectDataResponse load(ObjectDataRequest objectDataRequest) throws Exception {
        throw new ApiProblemException(
                "https://services.manywho.com/api/dummy/1/data",
                401,
                "You is unauthorised, <strong>boy.</strong>",
                headers.getRequestHeaders(),
                "Unauthorised"
        );
    }

    @Path("/data")
    @PUT
    public ObjectDataResponse save(ObjectDataRequest objectDataRequest) throws Exception {
        throw new ServiceProblemException(
                "https://services.manywho.com/api/dummy/1/data",
                400,
                "Something bad happened",
                headers.getRequestHeaders(),
                "I just said, something bad happened",
                InvokeType.Forward,
                "group/action"
        );
    }
}
