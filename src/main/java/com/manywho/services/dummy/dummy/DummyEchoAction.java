package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name = "Dummy: Echo", summary = "Return the same value that was given as an input", uri = "dummy/echo")
public class DummyEchoAction implements Action {
    public static class Input {
        @Action.Input(name = "Input Value", contentType = ContentType.String)
        private String value;

        public String getValue() {
            return value;
        }
    }

    public static class Output {
        @Action.Output(name = "Output Value", contentType = ContentType.String)
        private String value;

        public Output(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
