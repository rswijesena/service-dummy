package com.manywho.services.dummy.auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/callback")
public class FakeIdProvider {
    @Path("/fake-idp")
    @GET
    public Response fakeIdProvider(
            @QueryParam("state") String state,
            @QueryParam("redirect_uri") String redirectUri) {

        String url = String.format("%s?code=1234&state=%s&redirect_uri=%s", redirectUri, state, redirectUri);

        return Response.seeOther(URI.create(url)).build();
    }
}
