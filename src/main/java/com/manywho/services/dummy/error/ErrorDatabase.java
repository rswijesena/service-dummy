package com.manywho.services.dummy.error;

import com.manywho.sdk.api.draw.content.Command;
import com.manywho.sdk.api.run.elements.type.ListFilter;
import com.manywho.sdk.services.database.Database;
import com.manywho.services.dummy.ApplicationConfiguration;

import java.util.List;

public class ErrorDatabase implements Database<ApplicationConfiguration, Error> {
    @Override
    public Error find(ApplicationConfiguration configuration, Command command, String id) {
        throw new RuntimeException("Exception loading type Error");
    }

    @Override
    public List<Error> findAll(ApplicationConfiguration configuration, Command command, ListFilter filter) {
        throw new RuntimeException("Exception loading type Error");
    }

    @Override
    public Error create(ApplicationConfiguration configuration, Error object) {
        throw new RuntimeException("Exception saving type Error");
    }

    @Override
    public List<Error> create(ApplicationConfiguration configuration, List<Error> objects) {
        throw new RuntimeException("Exception saving type Error");
    }

    @Override
    public void delete(ApplicationConfiguration configuration, Error object) {
        throw new RuntimeException("Exception deleting type Error");
    }

    @Override
    public void delete(ApplicationConfiguration configuration, List<Error> objects) {
        throw new RuntimeException("Exception deleting type Error");
    }

    @Override
    public Error update(ApplicationConfiguration configuration, Error object) {
        throw new RuntimeException("Exception saving type Error");
    }

    @Override
    public List<Error> update(ApplicationConfiguration configuration, List<Error> objects) {
        throw new RuntimeException("Exception saving type Error");
    }
}
