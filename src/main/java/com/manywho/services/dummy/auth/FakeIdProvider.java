package com.manywho.services.dummy.auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Path("/callback")
public class FakeIdProvider {
    @Path("/fake-idp")
    @GET
    public Response fakeIdProvider(
            @QueryParam("state") String state,
            @QueryParam("redirect_uri") String redirectUri) throws UnsupportedEncodingException {

        String decodedRedirectUri = URLDecoder.decode(redirectUri, StandardCharsets.UTF_8.toString());

        String url = String.format("%s?code=1234&state=%s&redirect_uri=%s", decodedRedirectUri, state, decodedRedirectUri);

        return Response.seeOther(URI.create(url)).build();
    }
}
