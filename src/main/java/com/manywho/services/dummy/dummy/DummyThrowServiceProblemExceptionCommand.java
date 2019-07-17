package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.run.ServiceProblemException;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.dummy.ApplicationConfiguration;

import org.apache.http.HttpStatus;

public class DummyThrowServiceProblemExceptionCommand implements ActionCommand<ApplicationConfiguration, DummyThrowServiceProblemExceptionAction, Void, Void> {
    @Override
    public ActionResponse<Void> execute(ApplicationConfiguration configuration, ServiceRequest request, Void input) {
        throw new ServiceProblemException(request.getUri(), HttpStatus.SC_INTERNAL_SERVER_ERROR, "A service problem occurred");
    }
}
