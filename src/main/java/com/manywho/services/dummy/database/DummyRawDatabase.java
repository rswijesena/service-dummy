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
    public List<MObject> findAll(ApplicationConfiguration applicationConfiguration, ObjectDataType objectDataType,
                                 Command command, ListFilter listFilter, List<MObject> objects) {

        List<MObject> mObjects = new ArrayList<>();

        if (objectDataType.getDeveloperName().equals("hn-binding")) {
            if (objects == null || objects.size()==0 || containsOneOfOurCurrentHeadlines(objects, "How (not) to sign a JSON object")) {
                mObjects.add(createHackerNewsHeadlines());
            }

            return mObjects;
        } else if (objectDataType.getDeveloperName().equals("bbc-binding")) {
            if (objects == null || objects.size() == 0) {
                // as an example we will use the passed objects to filter the results
                mObjects.add(createBBCHeadlines());
                mObjects.add(createBBCHeadlines2());

                return mObjects;
            } else {
                if (containsOneOfOurCurrentHeadlines(objects, "UK heatwave set to break records")) {
                    mObjects.add(createBBCHeadlines());
                }

                if (containsOneOfOurCurrentHeadlines(objects, "Andy Murray will not play at Flushing Meadows")) {
                    mObjects.add(createBBCHeadlines2());
                }

                return mObjects;
            }
        }

        throw new RuntimeException(String.format("The type %s is not supported", objectDataType.getDeveloperName()));
    }

    private boolean containsOneOfOurCurrentHeadlines(List<MObject> objects, String headline) {
        return objects
                .stream()
                .anyMatch(obj -> obj.getProperties().stream().anyMatch(p -> headline.equals(p.getContentValue())));
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

    private MObject createHackerNewsHeadlines() {
        Property propertyTitleHackerNews = new Property("headlines", "How (not) to sign a JSON object");
        Property propertyPointsHackerNews = new Property("points", "3");

        // linked article
        Property titleLinkedArticle = new Property("headlines", "Catj: A new way to display JSON files");
        Property pointLinkedArticle = new Property("points", "388");

        MObject linkedArticle = new MObject("linked-articles", "hn-1-1", Arrays.asList(titleLinkedArticle, pointLinkedArticle));

        // add linked article as property
        Property propertyLinkedArticlesHackerNews = new Property("linked-articles", Arrays.asList(linkedArticle));

        return new MObject("hn-binding", "hn-1",
                Arrays.asList(propertyTitleHackerNews, propertyPointsHackerNews, propertyLinkedArticlesHackerNews));
    }

    private MObject createBBCHeadlines() {
        Property propertyTitleBBC = new Property("headlines", "UK heatwave set to break records");

        // created linked article
        MObject linkedArticle = new MObject("linked-articles", "bbc-1-1",
                Arrays.asList(new Property("headlines", "Hot weather: How to sleep in a heatwave")));

        Property propertyLinkedArticlesBBC = new Property("linked-articles", Arrays.asList(linkedArticle));

        return new MObject("bbc-binding", "bbc-1" , Arrays.asList(propertyTitleBBC, propertyLinkedArticlesBBC));
    }

    private MObject createBBCHeadlines2() {
        Property propertyTitleBBC = new Property("headlines", "Andy Murray will not play at Flushing Meadows");
        Property propertkyLinkedArticlesBBC = new Property("linked-articles", new ArrayList());

        return new MObject("bbc-binding", "bbc-2",  Arrays.asList(propertyTitleBBC, propertkyLinkedArticlesBBC));
    }
}

