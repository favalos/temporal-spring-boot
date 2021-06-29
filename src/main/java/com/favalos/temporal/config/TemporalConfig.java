package com.favalos.temporal.config;

import com.favalos.temporal.activity.GreetActivityImpl;
import com.favalos.temporal.workflow.GreetWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class TemporalConfig {

    private WorkerFactory factory;

    @Bean
    public WorkflowClient getClient() {

        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        return client;
    }

    @Bean
    public WorkerFactory getGreetWorkflow(
            @Autowired WorkflowClient client) {

        factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker(Constants.HELLO_WORLD_TASK_QUEUE);

        worker.registerWorkflowImplementationTypes(GreetWorkflowImpl.class);
        worker.registerActivitiesImplementations(new GreetActivityImpl());

        factory.start();

        return factory;
    }

    @PreDestroy
    public void stop() {

        factory.shutdown();
    }
}
