package com.manywho.services.dummy.actions;

import com.manywho.sdk.entities.describe.DescribeValueCollection;
import com.manywho.sdk.services.describe.actions.AbstractAction;

public class DummyThrowException extends AbstractAction {
    @Override
    public String getUriPart() {
        return "dummy/throwexception";
    }

    @Override
    public String getDeveloperName() {
        return "Dummy: Throw Exception";
    }

    @Override
    public String getDeveloperSummary() {
        return "Throw an exception from the service";
    }

    @Override
    public DescribeValueCollection getServiceInputs() {
        return new DescribeValueCollection() {{

        }};
    }

    @Override
    public DescribeValueCollection getServiceOutputs() {
        return new DescribeValueCollection() {{

        }};
    }
}
