package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.CategoryPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

/**
 * By Dimple Patel
 **/
public class CategorySteps
{
    @Step("Creating store with id:{0},name:{1}")
    public ValidatableResponse createCategory(String id, String name)
    {
        CategoryPojo categoryPojo=CategoryPojo.getCategoryPojo(id,name);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(categoryPojo)
                .when()
                .post(Path.CATEGORY)
                .then();
    }
    @Step("Getting the category information with name: {1}")
    public HashMap<String, Object> getCategoryInfoByName (String name,String id)
    {
        String p1 = "data.findAll{it.name=='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .queryParams("id",id)
                .when()
                .get(Path.CATEGORY)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name + p2);
    }

    @Step("Updating category information with id: {0}, name: {1}")
    public ValidatableResponse updateCategory(String id, String name)
    {
        CategoryPojo categoryPojo = CategoryPojo.getCategoryPojo(id,name);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .body(categoryPojo)
                .when()
                .put(Path.CATEGORY+EndPoints.UPDATE_CATEGORY_BY_ID)
                .then();
    }
    @Step("Deleting category information with id: {0}")
    public ValidatableResponse deleteCategory (String id){
        return SerenityRest
                .given()
                .pathParam("id", id)
                .when()
                .delete(Path.CATEGORY+EndPoints.DELETE_CATEGORY_BY_ID)
                .then();
    }
    @Step("Getting category information with id")
    public ValidatableResponse getCategoryByID (String id){
        return SerenityRest
                .given()
                .pathParam("id",id)
                .when()
                .get(Path.CATEGORY+EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then();
    }
    @Step("Getting category information with id: {0}")
    public ValidatableResponse getAllCategories ()
    {
        return SerenityRest
                .given()
                .when()
                .get(Path.CATEGORY)
                .then();
    }
}
