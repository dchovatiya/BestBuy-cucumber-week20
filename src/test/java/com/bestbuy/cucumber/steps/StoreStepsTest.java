package com.bestbuy.cucumber.steps;

import com.bestbuy.bestbuyinfo.StoreSteps;
import com.bestbuy.constants.Path;
import com.bestbuy.testbase.TestBase;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.BeforeClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasValue;

/**
 * By Dimple Patel
 **/
public class StoreStepsTest
{
    static ValidatableResponse response;
    static int storeID;

    @Steps
    StoreSteps storeSteps;
    @When("^User sends a GET request to stores endpoint$")
    public void userSendsAGETRequestToStoresEndpoint()
    {
        response=storeSteps.getAllStoreInfo().log().all().statusCode(200);
    }
    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }
    @When("^I create a new store by providing the following information$")
    public void iCreateANewStoreByProvidingTheFollowingInformation(DataTable dataTable)
    {
        List<List<String>> storeFields=dataTable.asLists(String.class);
        for (List<String> storeField:storeFields) {
            response=storeSteps.createStore(storeField.get(0), storeField.get(1), storeField.get(2),
                    storeField.get(3), storeField.get(4), storeField.get(5), storeField.get(6), Double.parseDouble(storeField.get(7)),
                    Double.parseDouble(storeField.get(8)), storeField.get(9));
            response.log().all().statusCode(201);
        }
    }
    @Then("^I verify that the store with storeId is created$")
    public void iVerifyThatTheStoreWithStoreIdIsCreated()
    {
        storeID=response.extract().path("id");
        System.out.println(storeID);
    }
    @When("^I update a store record by passing the information  \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" " +
            "\"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iUpdateAStoreRecordByPassingTheInformationAnd(String name, String type, String address, String address2, String city,
                                                              String state, String zip, double lat, double lng, String hours)
    {
        response=storeSteps.updateStore(storeID,name,type,address,address2,city,state,zip,lat,lng,hours);
        response.log().all().statusCode(200);
    }
    @Then("^Verify that the store with \"([^\"]*)\" is created$")
    public void verifyThatTheStoreWithIsCreated(String name)
    {
        response.statusCode(200);
        HashMap<String,Object> value=storeSteps.getStoreInfoByName(name,storeID);
        Assert.assertThat(value,hasValue(name));
    }
    @When("^I delete a store by providing id$")
    public void iDeleteAStoreByProvidingId()
    {
        response=storeSteps.deleteStore(storeID).statusCode(200);
        response.log().all();
    }
    @Then("^verify that the store is deleted$")
    public void verifyThatTheStoreIsDeleted()
    {
        storeSteps.getStoreById(storeID).log().all().statusCode(404);
    }
}
