package com.favalos.temporal.workflow;

import com.favalos.temporal.activity.GreetActivity;
import com.favalos.temporal.config.Constants;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class GreetWorkflowImpl implements GreetWorkflow {

    private ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofMinutes(5))
            .setTaskQueue(Constants.HELLO_WORLD_TASK_QUEUE)
            .build();

    private GreetActivity greetActivity = Workflow.newActivityStub(GreetActivity.class, activityOptions);

    @Override
    public String greet(String name) {

        return greetActivity.greet(name) ;
    }
}
