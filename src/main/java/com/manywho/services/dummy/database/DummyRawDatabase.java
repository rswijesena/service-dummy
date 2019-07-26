package com.manywho.services.dummy.database;

import com.manywho.sdk.api.draw.content.Command;
import com.manywho.sdk.api.run.elements.type.ListFilter;
import com.manywho.sdk.api.run.elements.type.MObject;
import com.manywho.sdk.api.run.elements.type.ObjectDataType;
import com.manywho.sdk.api.run.elements.type.Property;
import com.manywho.sdk.services.database.RawDatabase;
import com.manywho.services.dummy.ApplicationConfiguration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyRawDatabase implements RawDatabase<ApplicationConfiguration> {
    @Override
    public MObject find(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, Command command, String id) {
        return null;
    }

    @Override
    public List<MObject> findAll(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, Command command, ListFilter listFilter) {
        List<MObject> objects = new ArrayList<>();

        if (objectDataType.getDeveloperName().equals("hn-binding")) {
            objects.add(createHackerNewsHeadlines());
            return objects;
        } else if (objectDataType.getDeveloperName().equals("bbc-binding")) {
            objects.add(createBBCHeadlines());
            return objects;
        }

        throw new RuntimeException(String.format("The type %s is not supported", objectDataType.getDeveloperName()));
    }

    @Override
    public MObject create(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, MObject mObject) {
        return null;
    }

    @Override
    public List<MObject> create(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, List<MObject> list) {
        return null;
    }

    @Override
    public void delete(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, MObject mObject) {

    }

    @Override
    public void delete(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, List<MObject> list) {

    }

    @Override
    public MObject update(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, MObject mObject) {
        return null;
    }

    @Override
    public List<MObject> update(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType, List<MObject> list) {
        return null;
    }

    private MObject createBBCHeadlines() {
        Property propertyTitleBBC = new Property("headlines", "UK heatwave set to break records");
        Property propertyLinkedArticlesBBC = new Property("linked-articles", Arrays.asList(createBBCLinkedArticle()));

        MObject object = new MObject("bbc-binding",  Arrays.asList(propertyTitleBBC, propertyLinkedArticlesBBC));
        object.setExternalId("1");

        return object;
    }

    private MObject createBBCLinkedArticle() {
        Property titleLinkedArticle = new Property("headlines", "Hot weather: How to sleep in a heatwave");

        return new MObject("linked-articles", Arrays.asList(titleLinkedArticle));
    }

    private MObject createHackerNewsHeadlines() {
        Property propertyTitleHackerNews = new Property("headlines", "How (not) to sign a JSON object");
        Property propertyPointsHackerNews = new Property("points", "3");
        Property propertyLinkedArticlesHackerNews = new Property("linked-articles", Arrays.asList(createHackerNewsLinkedArticle()));


        MObject object = new MObject("hn-binding", Arrays.asList(propertyTitleHackerNews, propertyPointsHackerNews, propertyLinkedArticlesHackerNews));
        object.setExternalId("2");

        return object;
    }

    private MObject createHackerNewsLinkedArticle() {
        Property titleLinkedArticle = new Property("headlines", "Catj: A new way to display JSON files");
        Property pointLinkedArticle = new Property("points", "388");

        return new MObject("linked-articles", Arrays.asList(titleLinkedArticle, pointLinkedArticle));
    }
}
