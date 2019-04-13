package com.manywho.services.dummy.error;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

@Type.Element(name = "Error")
public class Error implements Type {
    @Type.Property(name = "Message", contentType = ContentType.String, bound = false)
    private String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
