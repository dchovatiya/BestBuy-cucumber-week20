package com.bestbuy.cucumber.steps;

import com.bestbuy.bestbuyinfo.CategorySteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

/**
 * By Dimple Patel
 **/
public class CategoryStepsTest
{
    static ValidatableResponse response;
    static String id = "abcat" + TestUtils.getRandomValue();
    static String name="Epic Gifts";


    @Steps
    CategorySteps categorySteps;
    @When("^User sends a GET request to category endpoint$")
    public void userSendsAGETRequestToCategoryEndpoint()
    {
        response=categorySteps.getAllCategories().log().all().statusCode(200);
    }
    @Then("^User must get back a valid status code$")
    public void userMustGetBackAValidStatusCode()
    {
        response.statusCode(200);
    }

    @When("^I create a new category record by providing id and name$")
    public void iCreateANewCategoryRecordByProvidingIdAndName()
    {
        response=categorySteps.createCategory(id,name).log().all().statusCode(201);
    }

    @Then("^Verify that the category with categoryID is created$")
    public void verifyThatTheCategoryWithCategoryIDIsCreated()
    {
        HashMap<String, Object> value = categorySteps.getCategoryInfoByName(name,id);
        Assert.assertThat(value, hasValue(name));
    }
    @When("^I update a category record by passing the name$")
    public void iUpdateACategoryRecordByPassingTheName()
    {
        name=name+TestUtils.getRandomText();
        response=categorySteps.updateCategory(id,name).log().all().statusCode(200);
        response = categorySteps.getCategoryByID(id).log().all().statusCode(200);
    }

    @When("^I delete a category by providing id$")
    public void iDeleteACategoryByProvidingId()
    {
        response=categorySteps.deleteCategory(id).log().all().statusCode(200);
    }

    @Then("^verify that the category is deleted$")
    public void verifyThatTheCategoryIsDeleted()
    {
        categorySteps.getCategoryByID(id).log().all().statusCode(404);
    }


}
