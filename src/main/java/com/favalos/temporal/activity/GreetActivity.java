package com.favalos.temporal.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface GreetActivity {

    @ActivityMethod
    String greet(String name);

}
