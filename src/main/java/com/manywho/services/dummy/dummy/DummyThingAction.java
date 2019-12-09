package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.dummy.dummy.types.Thing;

@Action.Metadata(name = "Dummy", summary = "Dummy action that does nothing", uri = "dummy/dummy")
public class DummyThingAction implements Action {
    public static class Input {
        @Action.Input(name = "Thing", contentType = ContentType.Object)
        private Thing thing;

        public Thing getName() {
            return thing;
        }
    }

    public static class Output {
        @Action.Output(name = "Body", contentType = ContentType.String)
        private String body;

        public Output(String body) {
            this.body = body;
        }

        public String getBody() {
            return body;
        }
    }
}
