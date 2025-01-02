package com.inditex.price.functional;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "usage")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "html:target/cucumber-reports.html")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.inditex.price.functional.steps,com.inditex.price.functional.hooks")
class CucumberRunnerTest {

}
