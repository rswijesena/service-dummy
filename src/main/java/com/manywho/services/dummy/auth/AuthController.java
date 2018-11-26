package com.manywho.services.dummy.auth;

import com.google.common.base.Strings;
import com.manywho.sdk.entities.UserObject;
import com.manywho.sdk.entities.run.elements.type.*;
import com.manywho.sdk.entities.run.elements.type.Object;
import com.manywho.sdk.entities.security.AuthenticatedWho;
import com.manywho.sdk.entities.security.AuthenticatedWhoResult;
import com.manywho.sdk.entities.security.AuthenticationCredentials;
import com.manywho.sdk.enums.AuthenticationStatus;
import com.manywho.sdk.enums.AuthorizationType;
import com.manywho.sdk.services.annotations.AuthorizationRequired;
import com.manywho.sdk.services.controllers.AbstractController;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController extends AbstractController {

    @Context
    private UriInfo uriInfo;

    @Path("/authentication")
    @POST
    public AuthenticatedWhoResult authentication(AuthenticationCredentials authenticationCredentials) throws Exception {
        AuthenticatedWhoResult authenticatedWhoResult = new AuthenticatedWhoResult();

        authenticatedWhoResult.setDirectoryId( "Dummy Directory");
        authenticatedWhoResult.setDirectoryName( "Dummy Directory");
        authenticatedWhoResult.setEmail("user1@example.com");
        authenticatedWhoResult.setFirstName("Jon");
        authenticatedWhoResult.setIdentityProvider("Dummy Identity Provider");
        authenticatedWhoResult.setLastName("Doe");
        authenticatedWhoResult.setStatus(AuthenticationStatus.Authenticated);
        authenticatedWhoResult.setTenantName("Tenant 1");
        authenticatedWhoResult.setToken("user1token");
        authenticatedWhoResult.setUserId( "user1");
        authenticatedWhoResult.setUsername("user1");

        return authenticatedWhoResult;
    }

    @Path("/authorization")
    @POST
    @AuthorizationRequired
    public ObjectDataResponse authorization(ObjectDataRequest objectDataRequest, @HeaderParam("X-Forwarded-Proto") String xForwardedProto) throws Exception {
        AuthenticatedWho authenticatedWho = getAuthenticatedWho();

        UserObject userObject;
        URI host = baseUri(xForwardedProto);

        if (authenticatedWho.getToken().equals("NONE")) {
            userObject = new UserObject("UNKNOWN", AuthorizationType.Oauth2,
                    host + "callback/fake-idp?", "401");
        } else {
            userObject = new UserObject("Dummy Directory", AuthorizationType.Oauth2,
                    "", "200");
        }

        return new ObjectDataResponse(userObject);
    }

    @Path("/authorization/group")
    @POST
    public ObjectDataResponse groups(ObjectDataRequest objectDataRequest) throws Exception {
        if (objectDataRequest.getListFilter() == null) {

            return loadGroupCollection(1, 10, true);
        } else if (objectDataRequest.getListFilter().getSearch() != null &&
                "group1".equals(objectDataRequest.getListFilter().getSearch().toLowerCase())) {

            return loadGroupCollection(1, 1, false);
        } else if (objectDataRequest.getListFilter().getOffset() == 0) {

            return loadGroupCollection(1, 10, true);
        } else {

            return loadGroupCollection(11, 12, false);
        }
    }

    @Path("/authorization/group/attribute")
    @POST
    public ObjectDataResponse groupAttributes(ObjectDataRequest objectDataRequest) throws Exception {
        return new ObjectDataResponse(loadGroupAttributes());
    }

    @Path("/authorization/user")
    @POST
    public ObjectDataResponse users(ObjectDataRequest objectDataRequest) throws Exception {

        if (objectDataRequest.getListFilter() == null) {

            return loadUserCollection(1, 10, true);
        } else if (objectDataRequest.getListFilter().getSearch() != null &&
                "user1".equals(objectDataRequest.getListFilter().getSearch().toLowerCase())) {

            return loadUserCollection(1, 1, false);
        } else if (objectDataRequest.getListFilter().getOffset() == 0) {

            return loadUserCollection(1, 10, true);
        } else {

            return loadUserCollection(11, 12, false);
        }

    }

    @Path("/authorization/user/attribute")
    @POST
    public ObjectDataResponse userAttributes(ObjectDataRequest objectDataRequest) throws Exception {
        return new ObjectDataResponse(loadUsersAttributes());
    }

    private Object loadGroupAttributes() {
        PropertyCollection properties = new PropertyCollection();
        properties.add(new Property("Label", "Users"));
        properties.add(new Property("Value", "users"));

        Object object = new Object();
        object.setDeveloperName("AuthenticationAttribute");
        object.setExternalId("users");
        object.setProperties(properties);

        return object;
    }

    private ObjectDataResponse loadGroupCollection(Integer idIni, Integer IdEnd, boolean hasMore) {
        ObjectCollection groupCollection = new ObjectCollection();

        for (Integer i = idIni; i<(IdEnd+1); i++) {
            groupCollection.add(loadGroup(i.toString()));
        }

        ObjectDataResponse objectDataResponse =  new ObjectDataResponse(groupCollection);
        objectDataResponse.setHasMoreResults(hasMore);

        return objectDataResponse;
    }

    private Object loadGroup(String groupId) {
        PropertyCollection properties = new PropertyCollection();

        properties.add(new Property("AuthenticationId", "group" + groupId));
        properties.add(new Property("FriendlyName", "Group " + groupId));
        properties.add(new Property("DeveloperSummary", "Group " + groupId));

        Object object = new Object();
        object.setDeveloperName("GroupAuthorizationGroup");
        object.setExternalId("group" + groupId);
        object.setProperties(properties);

        return object;
    }

    private Object loadUsersAttributes() {
        PropertyCollection properties = new PropertyCollection();
        properties.add(new Property("Label", "Account ID"));
        properties.add(new Property("Value", "accountId"));

        Object object = new Object();
        object.setDeveloperName("AuthenticationAttribute");
        object.setExternalId("accountID");
        object.setProperties(properties);

        return object;
    }

    private ObjectDataResponse loadUserCollection(Integer idIni, Integer IdEnd, boolean hasMore) {
        ObjectCollection userCollection = new ObjectCollection();

        for (Integer i = idIni; i<(IdEnd+1); i++) {
            userCollection.add(loadUser(i.toString()));
        }

        ObjectDataResponse objectDataResponse =  new ObjectDataResponse(userCollection);
        objectDataResponse.setHasMoreResults(hasMore);

        return objectDataResponse;
    }

    private Object loadUser(String userId) {
        PropertyCollection properties = new PropertyCollection();

        properties.add(new Property("AuthenticationId", "user" + userId));
        properties.add(new Property("FriendlyName",  "User " + userId));
        properties.add(new Property("DeveloperSummary",  "User "+ userId));

        Object object = new Object();
        object.setDeveloperName("GroupAuthorizationUser");
        object.setExternalId("user"+ userId);
        object.setProperties(properties);

        return object;
    }

    private URI baseUri(String headerProtocol) {

        if (Strings.isNullOrEmpty(headerProtocol) == false) {
            UriBuilder uri = uriInfo.getBaseUriBuilder();
            uri.scheme(headerProtocol);

            if (headerProtocol.toLowerCase().equals("https")) {
                uri.port(443);
            }

            return uri.build();
        } else {
            return uriInfo.getBaseUri();
        }
    }
}
