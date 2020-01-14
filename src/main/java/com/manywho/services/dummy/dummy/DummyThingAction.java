package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;
import com.manywho.services.dummy.dummy.types.Thing;
import com.manywho.sdk.services.types.system.$File;;

@Action.Metadata(name = "DummyThing", summary = "Dummy action that does nothing. Accepts complex types as input", uri = "dummy/thing")
public class DummyThingAction implements Action {
    public static class Input {
        @Action.Input(name = "Thing", contentType = ContentType.Object)
        private Thing thing;

        @Action.Input(name = "File", contentType = ContentType.Object)
        private $File file;

        public Thing getName() {
            return thing;
        }

        public $File getFile() {
            return file;
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
