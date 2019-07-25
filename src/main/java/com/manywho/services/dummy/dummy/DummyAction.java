package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name = "Dummy", summary = "Dummy action that does nothing", uri = "dummy/dummy")
public class DummyAction implements Action {
    public static class Input {
        @Action.Input(name = "Name", contentType = ContentType.String)
        private String name;

        @Action.Input(name = "Age", contentType = ContentType.Number)
        private int age;

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
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
