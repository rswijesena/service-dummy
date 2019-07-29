package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.InvokeType;
import com.manywho.sdk.api.run.elements.config.ServiceRequest;
import com.manywho.sdk.services.actions.ActionCommand;
import com.manywho.sdk.services.actions.ActionResponse;
import com.manywho.services.dummy.ApplicationConfiguration;
import com.manywho.services.dummy.dummy.DummyEchoAction.Input;
import com.manywho.services.dummy.dummy.DummyEchoAction.Output;

public class DummyEchoCommand implements ActionCommand<ApplicationConfiguration, DummyEchoAction, Input, Output> {
    @Override
    public ActionResponse<Output> execute(ApplicationConfiguration configuration, ServiceRequest request, Input input) {
        Output output = new Output(input.getValue());

        return new ActionResponse<>(output, InvokeType.Forward);
    }
}
