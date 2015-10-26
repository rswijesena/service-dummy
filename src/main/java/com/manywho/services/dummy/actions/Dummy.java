package com.manywho.services.dummy.actions;

import com.manywho.sdk.entities.describe.DescribeValue;
import com.manywho.sdk.entities.describe.DescribeValueCollection;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.services.describe.actions.AbstractAction;

public class Dummy extends AbstractAction {
    @Override
    public String getUriPart() {
        return "dummy/dummy";
    }

    @Override
    public String getDeveloperName() {
        return "Dummy";
    }

    @Override
    public String getDeveloperSummary() {
        return "Dummy action";
    }

    @Override
    public DescribeValueCollection getServiceInputs() {
        return new DescribeValueCollection() {{
            add(new DescribeValue("Name", ContentType.String));
            add(new DescribeValue("Age", ContentType.Number));
        }};
    }

    @Override
    public DescribeValueCollection getServiceOutputs() {
        return new DescribeValueCollection() {{

        }};
    }
}
