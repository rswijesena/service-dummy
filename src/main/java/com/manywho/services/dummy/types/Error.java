package com.manywho.services.dummy.types;

import com.manywho.sdk.entities.draw.elements.type.*;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.services.describe.types.AbstractType;

public class Error extends AbstractType {
    public final static String NAME = "Error";

    @Override
    public String getDeveloperName() {
        return NAME;
    }

    @Override
    public TypeElementBindingCollection getBindings() {
        return new TypeElementBindingCollection() {{
            add(new TypeElementBinding("Error", "Error bindings", NAME, new TypeElementPropertyBindingCollection() {{
                add(new TypeElementPropertyBinding("Message", "Message"));
            }}));
        }};
    }

    @Override
    public TypeElementPropertyCollection getProperties() {
        return new TypeElementPropertyCollection() {{
            add(new TypeElementProperty("Message", ContentType.String));
        }};
    }
}
