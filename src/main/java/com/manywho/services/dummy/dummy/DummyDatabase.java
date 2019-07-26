package com.manywho.services.dummy.dummy;

import com.google.common.collect.Lists;
import com.manywho.sdk.api.draw.content.Command;
import com.manywho.sdk.api.run.elements.type.ListFilter;
import com.manywho.sdk.api.run.elements.type.MObject;
import com.manywho.sdk.api.run.elements.type.ObjectDataType;
import com.manywho.sdk.services.database.ReadOnlyDatabase;
import com.manywho.services.dummy.ApplicationConfiguration;

import java.util.List;

public class DummyDatabase implements ReadOnlyDatabase<ApplicationConfiguration, Dummy> {
    @Override
    public Dummy find(ApplicationConfiguration configuration, ObjectDataType objectDataType, Command command, String id) {
        return new Dummy("123", "Jonjo", 23);
    }

    @Override
    public List<Dummy> findAll(ApplicationConfiguration configuration, ObjectDataType objectDataType, Command command, ListFilter filter) {
        return Lists.newArrayList(
                new Dummy("123", "Jonjo", 23),
                new Dummy("456", "Jonjo", 23)
        );
    }
}
