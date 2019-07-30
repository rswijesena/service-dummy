package com.manywho.services.dummy.dummy;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.api.InvokeType;
import com.manywho.sdk.api.run.EngineValue;
import com.manywho.sdk.api.run.elements.config.ServiceResponse;
import com.manywho.sdk.api.run.elements.type.ObjectDataRequest;
import com.manywho.sdk.api.security.AuthenticatedWho;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Path("/invalidresponse")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvalidResponseController {
    
    private final Provider<AuthenticatedWho> authenticatedWhoProvider;

    @Inject
    public InvalidResponseController(Provider<AuthenticatedWho> authenticatedWhoProvider) {
        this.authenticatedWhoProvider = authenticatedWhoProvider;
    }
    
    
    @Path("/invaliddate")
    @POST
    public Response invalid(ObjectDataRequest objectDataRequest) throws UnsupportedEncodingException {

        AuthenticatedWho authenticatedWho = authenticatedWhoProvider.get();
        
        ServiceResponse response = new ServiceResponse();
        response.setToken(objectDataRequest.getToken());
        response.setTenantId(authenticatedWho.getManyWhoTenantId());
        response.setInvokeType(InvokeType.Forward);
        
        EngineValue invalidDateValue = new EngineValue();
        invalidDateValue.setContentType(ContentType.DateTime);
        invalidDateValue.setContentValue("InvalidDate"); 
        invalidDateValue.setDeveloperName("Body");
        
        List<EngineValue> values = new ArrayList<EngineValue>();
        values.add(invalidDateValue);
        response.setOutputs(values);
        
        return Response.ok(response).build();
    }
}
