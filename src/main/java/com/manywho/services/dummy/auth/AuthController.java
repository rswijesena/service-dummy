package com.manywho.services.dummy.auth;

import com.google.common.base.Strings;
import com.manywho.sdk.entities.UserObject;
import com.manywho.sdk.entities.run.elements.config.Authorization;
import com.manywho.sdk.entities.run.elements.config.Group;
import com.manywho.sdk.entities.run.elements.config.User;
import com.manywho.sdk.entities.run.elements.type.*;
import com.manywho.sdk.entities.run.elements.type.Object;
import com.manywho.sdk.entities.security.AuthenticatedWho;
import com.manywho.sdk.entities.security.AuthenticatedWhoResult;
import com.manywho.sdk.entities.security.AuthenticationCredentials;
import com.manywho.sdk.enums.AuthenticationStatus;
import com.manywho.sdk.enums.AuthorizationType;
import com.manywho.sdk.services.annotations.AuthorizationRequired;
import com.manywho.sdk.services.controllers.AbstractController;
import org.apache.commons.collections4.CollectionUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController extends AbstractController {
    private final static Integer USERS_IN_DIRECTORY = 61;
    private final static Integer GROUPS_IN_DIRECTORY = 53;

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

        String status = getUserAuthorizationStatus(objectDataRequest.getAuthorization(), authenticatedWho);

        if (status.equals("401")) {
            userObject = new UserObject("UNKNOWN", AuthorizationType.Oauth2,
                    host + "callback/fake-idp?", "401");
        } else {
            userObject = new UserObject("Dummy Directory", AuthorizationType.Oauth2,
                    "", "200");
        }

        return new ObjectDataResponse(userObject);
    }


    public String getUserAuthorizationStatus(Authorization authorization, AuthenticatedWho user) {
        switch (authorization.getGlobalAuthenticationType()) {
            case Public:
                return "200";
            case AllUsers:
                if (!user.getUserId().equalsIgnoreCase("PUBLIC_USER")) {
                    return "200";
                } else {
                    return "401";
                }
            case Specified:
                if (!user.getUserId().equalsIgnoreCase("PUBLIC_USER")) {
                    String userId = user.getUserId();

                    if (CollectionUtils.isNotEmpty(authorization.getUsers())) {
                        for (User allowedUser:authorization.getUsers()) {
                            if (allowedUser.getAttribute().equalsIgnoreCase("accountId")
                                    && Objects.equals(allowedUser.getAuthenticationId(), userId)) {

                                return "200";
                            }
                        }
                    }

                    if (CollectionUtils.isNotEmpty(authorization.getGroups())) {
                        List<Object> groups = new ArrayList<>();
                        if (user.getUserId().equals("user1")) {
                            //user1 is member of group1
                            groups.add(loadGroup("1"));
                        }

                        for (Group group : authorization.getGroups()) {
                            if (groups.stream().anyMatch(m -> m.getExternalId().equals(group.getAuthenticationId()))) {
                                return "200";
                            }
                        }
                    }
                }
            default:
                return "401";
        }
    }

    @Path("/authorization/group")
    @POST
    public ObjectDataResponse groups(ObjectDataRequest objectDataRequest) throws Exception {
        ObjectCollection allGroups = loadGroups(USERS_IN_DIRECTORY);
        ObjectCollection groupsToReturn = new ObjectCollection();

        boolean hasMoreValues = true;

        if (objectDataRequest.getObjectData() != null) {
            for (MObject requestedGroups : objectDataRequest.getObjectData()) {
                if (requestedGroups.getDeveloperName().equals("GroupAuthorizationGroup")) {

                    String idToSearch = requestedGroups.getProperties().stream()
                            .filter(property -> property.getDeveloperName().equals("AuthenticationId"))
                            .findFirst()
                            .orElse(new Property("AuthenticationId", ""))
                            .getContentValue();

                    allGroups.stream()
                            .filter(u -> u.getExternalId().equals(idToSearch))
                            .findFirst()
                            .map(groupsToReturn::add);
                }
            }
        } else {

            hasMoreValues = searchObjectsByExternalIds(objectDataRequest, allGroups, groupsToReturn, hasMoreValues, GROUPS_IN_DIRECTORY);
        }

        return createResponse(groupsToReturn, hasMoreValues);
    }

    @Path("/authorization/group/attribute")
    @POST
    public ObjectDataResponse groupAttributes(ObjectDataRequest objectDataRequest) throws Exception {
        return new ObjectDataResponse(loadGroupAttributes());
    }

    @Path("/authorization/user")
    @POST
    public ObjectDataResponse users(ObjectDataRequest objectDataRequest) throws Exception {
        ObjectCollection allUsers = loadUsers(USERS_IN_DIRECTORY);
        ObjectCollection usersToReturn = new ObjectCollection();

        boolean hasMoreValues = true;

        if (objectDataRequest.getObjectData() != null) {
            for (MObject requestedUsers : objectDataRequest.getObjectData()) {
                if (requestedUsers.getDeveloperName().equals("GroupAuthorizationUser")) {

                    String idToSearch = requestedUsers.getProperties().stream()
                            .filter(property -> property.getDeveloperName().equals("AuthenticationId"))
                            .findFirst()
                            .orElse(new Property("AuthenticationId", ""))
                            .getContentValue();

                    allUsers.stream()
                            .filter(u -> u.getExternalId().equals(idToSearch))
                            .findFirst()
                            .map(usersToReturn::add);
                }
            }
        } else {
            hasMoreValues = searchObjectsByExternalIds(objectDataRequest, allUsers, usersToReturn, hasMoreValues, USERS_IN_DIRECTORY);
        }

        return createResponse(usersToReturn, hasMoreValues);
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

    private boolean searchObjectsByExternalIds(ObjectDataRequest objectDataRequest, ObjectCollection allObjects, ObjectCollection objectsToReturn, boolean hasMoreValues, Integer objectsInDirectory) {
        ObjectCollection objectCollection = allObjects;

        if (Strings.isNullOrEmpty(objectDataRequest.getListFilter().getSearch()) == false) {
            objectCollection = allObjects.stream()
                    .filter(o -> o.getExternalId().startsWith(objectDataRequest.getListFilter().getSearch()))
                    .collect(Collectors.toCollection(ObjectCollection::new));
        }

        int fromValue = 1;
        Integer toValue = objectsInDirectory;

        if (objectDataRequest.getListFilter() != null) {
            fromValue = objectDataRequest.getListFilter().getOffset();
            toValue = objectDataRequest.getListFilter().getOffset() + objectDataRequest.getListFilter().getLimit();
        }

        if (toValue >= objectCollection.size()) {
            hasMoreValues = false;
            toValue = objectCollection.size();
        }

        for (Integer counter = fromValue; counter < toValue; counter++) {
            objectsToReturn.add(allObjects.get(counter));
        }
        return hasMoreValues;
    }

    private Object loadGroup(String groupId) {
        PropertyCollection properties = new PropertyCollection();

        properties.add(new Property("AuthenticationId", "group " + groupId));
        properties.add(new Property("FriendlyName", "Group " + groupId));
        properties.add(new Property("DeveloperSummary", "Group " + groupId));

        Object object = new Object();
        object.setDeveloperName("GroupAuthorizationGroup");
        object.setExternalId("group " + groupId);
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


    private ObjectCollection loadGroups(Integer howManyGroups) {
        ObjectCollection groupCollection = new ObjectCollection();

        for (Integer i = 1; i <= howManyGroups; i++) {
            groupCollection.add(loadGroup(i.toString()));
        }

        return groupCollection;
    }


    private ObjectCollection loadUsers(Integer howManyUsers) {
        ObjectCollection userCollection = new ObjectCollection();

        for (Integer i = 1; i <= howManyUsers; i++) {
            userCollection.add(loadUser(i.toString()));
        }

        return userCollection;
    }

    private Object loadUser(String userId) {
        PropertyCollection properties = new PropertyCollection();

        properties.add(new Property("AuthenticationId", "user " + userId));
        properties.add(new Property("FriendlyName",  "User " + userId));
        properties.add(new Property("DeveloperSummary",  "User "+ userId));

        Object object = new Object();
        object.setDeveloperName("GroupAuthorizationUser");
        object.setExternalId("user "+ userId);
        object.setProperties(properties);

        return object;
    }

    private ObjectDataResponse createResponse(ObjectCollection objectCollection, boolean hasMore) {
        ObjectDataResponse objectDataResponse =  new ObjectDataResponse(objectCollection);
        objectDataResponse.setHasMoreResults(hasMore);

        return objectDataResponse;
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
