package com.manywho.services.dummy.dummy;

import com.google.common.collect.Lists;
import com.manywho.sdk.api.draw.content.Command;
import com.manywho.sdk.api.run.elements.type.ListFilter;
import com.manywho.sdk.api.run.elements.type.MObject;
import com.manywho.sdk.api.run.elements.type.ObjectDataType;
import com.manywho.sdk.services.database.Database;
import com.manywho.services.dummy.ApplicationConfiguration;

import java.util.List;

public class DummyDatabase implements Database<ApplicationConfiguration, Dummy> {
    @Override
    public Dummy find(ApplicationConfiguration configuration, ObjectDataType objectDataType, Command command, String id) {
        return new Dummy("123", "Jonjo", 23);
    }

    @Override
    public List<Dummy> findAll(ApplicationConfiguration configuration, ObjectDataType objectDataType, Command command, ListFilter filter, List<MObject> objects) {
        return Lists.newArrayList(
                new Dummy("123", "Jonjo", 23),
                new Dummy("456", "Jonjo", 23)
        );
    }

    @Override
    public Dummy create(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, Dummy dummy) {
        return null;
    }

    @Override
    public List<Dummy> create(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, List<Dummy> list) {
        return Lists.newArrayList(
                new Dummy("123", "Jonjo", 23),
                new Dummy("456", "Jonjo", 23)
        );
    }

    @Override
    public void delete(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, Dummy dummy) {

    }

    @Override
    public void delete(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, List<Dummy> list) {

    }

    @Override
    public Dummy update(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, Dummy dummy) {
        return null;
    }

    @Override
    public List<Dummy> update(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, List<Dummy> list) {
        return null;
    }
}
