package com.manywho.services.dummy.types;

import com.manywho.sdk.entities.draw.elements.type.*;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.services.describe.types.AbstractType;

public class Dummy extends AbstractType {
    public final static String NAME = "Dummy";

    @Override
    public String getDeveloperName() {
        return NAME;
    }

    @Override
    public TypeElementBindingCollection getBindings() {
        return new TypeElementBindingCollection() {{
            add(new TypeElementBinding("Dummy", "Dummy bindings", NAME, new TypeElementPropertyBindingCollection() {{
                add(new TypeElementPropertyBinding("Name", "Name"));
                add(new TypeElementPropertyBinding("Age", "Age"));
            }}));
        }};
    }

    @Override
    public TypeElementPropertyCollection getProperties() {
        return new TypeElementPropertyCollection() {{
            add(new TypeElementProperty("Name", ContentType.String));
            add(new TypeElementProperty("Age", ContentType.Number));
        }};
    }
}
