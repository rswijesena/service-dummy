package com.manywho.services.dummy;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.configuration.Configuration;

public class ApplicationConfiguration implements Configuration {

    @Configuration.Setting(name = "Include Types With Bindings", contentType = ContentType.Boolean)
    private boolean includeTypesWithBindings;

    public boolean getIncludeTypesWithBindings() {
        return includeTypesWithBindings;
    }
}
