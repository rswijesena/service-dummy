package com.manywho.services.dummy.dummy;

import javax.validation.constraints.NotNull;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.dummy.dummy.types.Thing;

@Action.Metadata(name = "Thing", summary = "Dummy action that also does nothing but accepting a complex object Thing as input", uri = "dummy/thing")
public class DummyThingAction implements Action {
    @NotNull(message = "A Thing is required")
    @Action.Input(name = "Thing", contentType = ContentType.Object)
    private Thing thing;

    public Thing getThing() {
        return thing;
    }
}
