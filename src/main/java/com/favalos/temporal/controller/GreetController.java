package com.favalos.temporal.controller;

import com.favalos.temporal.config.Constants;
import com.favalos.temporal.workflow.GreetWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @Autowired
    private WorkflowClient workflowClient;

    private WorkflowOptions options = WorkflowOptions.newBuilder()
            .setTaskQueue(Constants.HELLO_WORLD_TASK_QUEUE)
            .build();

    @GetMapping("/greet")
    public String getGreet(@RequestParam("name") String name) {

        GreetWorkflow greetWorkflow = workflowClient.newWorkflowStub(GreetWorkflow.class, options);

        return greetWorkflow.greet(name);
    }

}
