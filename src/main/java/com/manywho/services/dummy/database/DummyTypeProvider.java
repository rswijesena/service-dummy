package com.manywho.services.dummy.database;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.api.describe.DescribeServiceRequest;
import com.manywho.sdk.api.draw.elements.type.TypeElement;
import com.manywho.sdk.api.draw.elements.type.TypeElementBinding;
import com.manywho.sdk.api.draw.elements.type.TypeElementProperty;
import com.manywho.sdk.api.draw.elements.type.TypeElementPropertyBinding;
import com.manywho.sdk.services.types.TypeProvider;
import com.manywho.services.dummy.ApplicationConfiguration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyTypeProvider  implements TypeProvider<ApplicationConfiguration> {
    @Override
    public boolean doesTypeExist(ApplicationConfiguration applicationConfiguration, String name) {
        switch(name) {
            case "bbc-binding":
            case "hn-binding":
                return true;
            default:
                return false;
        }
    }

    @Override
    public List<TypeElement> describeTypes(ApplicationConfiguration applicationConfiguration, DescribeServiceRequest describeServiceRequest) {
        if (describeServiceRequest.hasConfigurationValues() == false || applicationConfiguration.getIncludeTypesWithBindings() == false) {
            return new ArrayList<>();
        }


        List<TypeElementProperty> propertiesHeadlines = new ArrayList<>();
        propertiesHeadlines.add(new TypeElementProperty("Headlines", ContentType.String));
        propertiesHeadlines.add(new TypeElementProperty("Points", ContentType.String));
        propertiesHeadlines.add(new TypeElementProperty("Linked Articles", ContentType.List, "Headlines In News"));

        List<TypeElementPropertyBinding> propertyBindingsBBC = new ArrayList<>();
        propertyBindingsBBC.add(new TypeElementPropertyBinding("Headlines", "headlines", "txt"));
        propertyBindingsBBC.add(new TypeElementPropertyBinding("Linked Articles", "linked-articles", "txt"));

        List<TypeElementPropertyBinding> propertyBindingsHackerNews = new ArrayList<>();
        propertyBindingsHackerNews.add(new TypeElementPropertyBinding("Headlines", "headlines", "txt"));
        propertyBindingsHackerNews.add(new TypeElementPropertyBinding("Points", "points", "txt"));
        propertyBindingsHackerNews.add(new TypeElementPropertyBinding("Linked Articles", "linked-articles", "txt"));

        List<TypeElementBinding> bindingsHeadlines = new ArrayList<>();
        bindingsHeadlines.add(new TypeElementBinding("BBC News", "Using BBC News Bindings", "bbc-binding", propertyBindingsBBC));
        bindingsHeadlines.add(new TypeElementBinding("Hacker News", "Using Hacker News Bindings", "hn-binding", propertyBindingsHackerNews));

        return Arrays.asList(new TypeElement("Headlines In News", propertiesHeadlines, bindingsHeadlines));
    }
}
