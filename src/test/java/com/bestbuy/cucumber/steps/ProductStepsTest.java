package com.bestbuy.cucumber.steps;

import com.bestbuy.bestbuyinfo.ProductsSteps;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasValue;

/**
 * By Dimple Patel
 **/
public class ProductStepsTest
{
    static ValidatableResponse response;
    static int productID;
    @Steps
    ProductsSteps productsSteps;


    @When("^User sends a GET request to products endpoint$")
    public void userSendsAGETRequestToProductsEndpoint()
    {
        response=productsSteps.getAllProductsRecords();
        response.log().all();
        //response.log().ifValidationFails();
    }

    @Then("^User must get a valid status code$")
    public void userMustGetAValidStatusCode()
    {
        //validating the response
        response.statusCode(200);
    }

    @When("^I create a new product record by providing the following information$")
    public void iCreateANewProductRecordByProvidingTheFollowingInformation(DataTable dataTable)
    {
        List<Map<String,String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String,String> e:list)
        {
            response=productsSteps.createProduct(e.get("name"),e.get("type"),Integer.parseInt(e.get("price")),e.get("upc"),Integer.parseInt(e.get("shipping")),e.get("description"),e.get("manufacturer"),e.get("model"),e.get("url"),e.get("image"));
            response.log().all().statusCode(201);//status code for creating
        }
    }
    @Then("^Verify that the product with productId is created$")
    public void verifyThatTheProductWithProductIdIsCreated()
    {
        productID=response.extract().path("id");
        System.out.println(productID);
    }
    @When("^I update a product record by passing the information  \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" " +
            "\"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iUpdateAProductRecordByPassingTheInformationAnd(String name, String type, int price, String upc, int shipping, String description,
                                                                String manufacturer, String model, String url, String image)
    {
        response=productsSteps.updateProduct(productID,name,type,price,upc,shipping,description,manufacturer,model,url,image);
        response.log().all().statusCode(200);
    }

    @Then("^Verify that the product with \"([^\"]*)\" is created$")
    public void verifyThatTheProductWithIsCreated(String type)
    {
        response.statusCode(200);
        HashMap<String, String> value=productsSteps.getProductInfoByType(productID);
        System.out.println(value);
        Assert.assertThat(value,hasValue(type));
        Assert.assertTrue(value.containsValue("Solid"));
        response.log().all();
        /*System.out.println(value.containsValue(type));
        //System.out.println(value.values());
        //System.out.println(value.get("type"));
        //String expectedResult=value.get("type");
        //Assert.assertEquals(expectedResult,hasValue(type));*/
    }
    @When("^I delete a product by providing id$")
    public void iDeleteAProductByProvidingId()
    {
        response=productsSteps.deleteProduct(productID).statusCode(200);
        response.log().all();
    }
    @Then("^verify that the product is deleted$")
    public void verifyThatTheProductIsDeleted()
    {
        productsSteps.getProductById(productID).log().all().statusCode(404);
    }
}
