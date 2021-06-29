package com.favalos.temporal.activity;

public class GreetActivityImpl implements GreetActivity {

    @Override
    public String greet(String name) {
        return String.format("Hola %s", name);
    }

}
