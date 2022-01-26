package com.bestbuy.cucumber;

import com.bestbuy.constants.Path;
import com.bestbuy.testbase.TestBase;
import cucumber.api.CucumberOptions;
import io.restassured.RestAssured;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * By Dimple Patel
 **/

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/java/resources/feature/")

public class CucumberRunner extends TestBase
{


}
