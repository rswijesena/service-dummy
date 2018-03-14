package com.manywho.services.dummy.controllers;

import com.manywho.sdk.entities.run.elements.type.MObject;
import com.manywho.sdk.entities.run.elements.type.ObjectCollection;
import com.manywho.sdk.entities.run.elements.type.ObjectDataRequest;
import com.manywho.sdk.entities.run.elements.type.ObjectDataResponse;
import com.manywho.sdk.entities.run.elements.type.Property;
import com.manywho.sdk.entities.run.elements.type.PropertyCollection;
import com.manywho.services.dummy.types.Dummy;
import com.manywho.services.dummy.types.Error;

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
        switch (objectDataRequest.getObjectDataType().getDeveloperName()) {
            case Dummy.NAME:
                PropertyCollection properties = new PropertyCollection();
                properties.add(new Property("Name", "Jonjo"));
                properties.add(new Property("Age", 23));

                ObjectCollection objects = new ObjectCollection();
                objects.add(new MObject(Dummy.NAME, "123", properties));
                objects.add(new MObject(Dummy.NAME, "456", properties));

                return new ObjectDataResponse(objects);

            case Error.NAME:
                throw new RuntimeException("Exception loading type Error");
        }

        return new ObjectDataResponse();
    }

    @Path("/data")
    @PUT
    public ObjectDataResponse save(ObjectDataRequest objectDataRequest) throws Exception {
        switch (objectDataRequest.getObjectDataType().getDeveloperName()) {
            case Error.NAME:
                throw new RuntimeException("Exception saving type Error");
        }

        return new ObjectDataResponse();
    }

    @Path("/delete")
    @POST
    public ObjectDataResponse delete(ObjectDataRequest objectDataRequest) throws Exception {
        switch (objectDataRequest.getObjectDataType().getDeveloperName()) {
            case Error.NAME:
                throw new RuntimeException("Exception deleting type Error");
        }

        return new ObjectDataResponse();
    }
}
