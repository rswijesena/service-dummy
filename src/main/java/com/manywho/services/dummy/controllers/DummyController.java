package com.manywho.services.dummy.controllers;

import com.manywho.sdk.entities.run.elements.config.ServiceRequest;
import com.manywho.sdk.entities.run.elements.config.ServiceResponse;
import com.manywho.sdk.enums.InvokeType;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/dummy")
@Consumes("application/json")
@Produces("application/json")
public class DummyController {

    @Path("/dummy")
    @POST
    public ServiceResponse dummy(ServiceRequest serviceRequest) throws Exception {
//        throw new Exception("Blah blah blah");
        return new ServiceResponse(InvokeType.Forward, serviceRequest.getToken());
    }
}
