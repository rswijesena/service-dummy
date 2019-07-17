package com.manywho.services.dummy.dummy;

import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name = "Dummy: Service Problem", summary = "Trigger a service problem exception", uri = "dummy/serviceproblem")
public class DummyThrowServiceProblemExceptionAction implements Action {

}
