package com.bestbuy.cucumber.steps;

import com.bestbuy.bestbuyinfo.ServiceSteps;
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
public class ServiceStepsTest
{
    static ValidatableResponse response;
    static String name="Off Licence Business";
    static int serviceId;
    @Steps
    ServiceSteps serviceSteps;
    @When("^User sends a GET request to service endpoint$")
    public void userSendsAGETRequestToServiceEndpoint()
    {
        response=serviceSteps.getAllServiceInformation().log().all().statusCode(200);
    }
    @Then("^User must get back a status code which is valid$")
    public void userMustGetBackAStatusCodeWhichIsValid()
    {
        response.statusCode(200);
    }
    @When("^I create a new service record by providing name$")
    public void iCreateANewServiceRecordByProvidingName()
    {
        response=serviceSteps.createService(name).log().all().statusCode(201);
    }
    @Then("^Verify that the service with serviceID is created$")
    public void verifyThatTheServiceWithServiceIDIsCreated()
    {
        serviceId=response.extract().path("id");
        System.out.println(serviceId);
        HashMap<String,Object>value =serviceSteps.getServiceInfoByName(name,serviceId);
        Assert.assertThat(value,hasValue(name));
    }
    @When("^I update a service record by passing the name$")
    public void iUpdateAServiceRecordByPassingTheName()
    {
        name=name+"_Updated";
        response=serviceSteps.updateService(serviceId,name).log().all().statusCode(200);
        response=serviceSteps.getServiceById(serviceId).log().all().statusCode(200);
    }
    @When("^I delete a service by providing id$")
    public void iDeleteAServiceByProvidingId()
    {
        response=serviceSteps.deleteService(serviceId).log().all().statusCode(200);

    }
    @Then("^verify that the service is deleted$")
    public void verifyThatTheServiceIsDeleted()
    {
        serviceSteps.getServiceById(serviceId).log().all().statusCode(404);
    }


}
