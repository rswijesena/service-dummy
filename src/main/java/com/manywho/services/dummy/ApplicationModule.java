package com.manywho.services.dummy;

import com.google.inject.AbstractModule;
import com.manywho.sdk.client.run.RunClient;
import com.manywho.sdk.services.providers.RunClientProvider;

public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RunClient.class).toProvider(RunClientProvider.class);
    }
}
