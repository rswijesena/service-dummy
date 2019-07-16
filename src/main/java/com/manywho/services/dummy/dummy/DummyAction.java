package com.manywho.services.dummy.dummy;

import java.time.OffsetDateTime;
import java.util.List;

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
        @Action.Output(name = "Date", contentType = ContentType.DateTime)
        private OffsetDateTime date;

        @Action.Output(name = "Modes", contentType = ContentType.List)
        private List<String> modes;

        public Output(OffsetDateTime date, List<String> modes) {
            this.date = date;
            this.modes = modes;
        }

        public OffsetDateTime getDate() {
            return date;
        }

        public List<String> getModes() {
            return modes;
        }
    }
}
