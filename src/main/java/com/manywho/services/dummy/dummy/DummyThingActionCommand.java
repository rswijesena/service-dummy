package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.InvokeType;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.dummy.ApplicationConfiguration;
import com.manywho.services.dummy.dummy.DummyThingAction.Input;
import com.manywho.services.dummy.dummy.DummyThingAction.Output;

public class DummyThingActionCommand implements ActionCommand<ApplicationConfiguration, DummyThingAction, Input, Output> {
    @Override
    public ActionResponse<Output> execute(ApplicationConfiguration configuration, ServiceRequest request, Input input) {
        Output output = new Output("Hello from the Dummy Service, thanks for the Thing " + input.getName());

        return new ActionResponse<>(output, InvokeType.Forward);
    }
}
