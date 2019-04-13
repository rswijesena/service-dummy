package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.dummy.ApplicationConfiguration;

public class DummyThrowExceptionActionCommand implements ActionCommand<ApplicationConfiguration, DummyThrowExceptionAction, Void, Void> {
    @Override
    public ActionResponse<Void> execute(ApplicationConfiguration configuration, ServiceRequest request, Void input) {
        throw new RuntimeException("Oh no! This is an exception");
    }
}
