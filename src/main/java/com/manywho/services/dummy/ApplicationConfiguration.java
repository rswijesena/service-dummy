package com.manywho.services.dummy;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.configuration.Configuration;

public class ApplicationConfiguration implements Configuration {

    @Configuration.Setting(name = "Hostname", contentType = ContentType.String, required = true)
    private String username;

    @Configuration.Setting(name = "Port", contentType = ContentType.Number, required = false)
    private String password;
}
