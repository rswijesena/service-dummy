package com.manywho.services.dummy.dummy;

import java.util.ArrayList;
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
        @Action.Output(name = "Body", contentType = ContentType.String)
        private String body;

        public Output(String body) {
            this.body = body;
            this.items = new ArrayList<>();

            this.items.add(new Dummy("1","John",25));
            this.items.add(new Dummy("2","David",38));
        }

        public String getBody() {
            return body;
        }

        @Action.Output(name = "Items", contentType = ContentType.List)
        private List<Dummy> items;

        public List<Dummy> getItems() {
            return items;
        }
    }
}
