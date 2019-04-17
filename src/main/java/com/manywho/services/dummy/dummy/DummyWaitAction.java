package com.manywho.services.dummy.dummy;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.actions.Action;

@Action.Metadata(name = "Dummy: Wait", summary = "Makes the service wait for a specified amount of time before pushing the flow forward", uri = "dummy/wait")
public class DummyWaitAction implements Action {
    public static class Input {
        @Action.Input(name = "Number of Seconds", contentType = ContentType.Number)
        private int numberOfSeconds;

        public int getNumberOfSeconds() {
            return numberOfSeconds;
        }
    }

    public static class Output {
        @Action.Output(name = "Message", contentType = ContentType.String)
        private String message;

        public Output(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
