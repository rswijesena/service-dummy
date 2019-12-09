package com.manywho.services.dummy.dummy.types;


import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

@Type.Element(name = "Thing", summary = "The Thing object structure")
public class Thing implements Type {
    @Type.Property(name = "Name", contentType = ContentType.String, bound = false)
    private String name;

    public String getName() {
        return name;
    }
}
