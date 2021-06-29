package com.favalos.temporal.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface GreetWorkflow {

    @WorkflowMethod
    String greet(String name);

}
