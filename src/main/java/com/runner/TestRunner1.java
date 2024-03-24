package com.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // Path of feature file
        features = "src/test/resources/featureFile",
        // Path of step definition file
        glue = "com.stepDefinitions",
        tags="@apiTest2"
)
public class TestRunner1 {
}
