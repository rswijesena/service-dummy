package com.manywho.services.dummy;

import com.manywho.sdk.services.BaseApplication;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Application extends BaseApplication {
    public Application() {
        registerSdk().packages("com.manywho.services.dummy");
    }
}
