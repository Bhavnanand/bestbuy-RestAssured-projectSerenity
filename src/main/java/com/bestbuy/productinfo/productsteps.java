package com.bestbuy.productinfo;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.constant.Path;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.vavr.collection.HashMap;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class productsteps {
    @Step("Creating product with  name:{0},type:{1},price:{2},shipping:{3},upc:{4},description:{5},manufacturer:{6},model:{7},url:{8} and image:{9}")
            public ValidatableResponse createProduct(String name,String type,String address,String Address2,String city,double price,int shipping,String upc, String description,
     String manufacturer,String model, String url,String image){
        ProductPojo  productPojo  = new ProductPojo();
        productPojo.setName( name);
        productPojo.setType(type);
        productPojo.setAddress(address);
        productPojo.setAddress2( Address2);
        productPojo.setCity( city);
        productPojo.setPrice(price);
        productPojo.setShipping( shipping);
        productPojo.setUpc( upc);
        productPojo.setDescription( description);
        productPojo.setManufacturer( manufacturer);
        productPojo.setModel(model);
        productPojo. setUrl(url);
        productPojo.setImage(image);


        return SerenityRest.given()
                .basePath(Path.PRODUCTS)
              .contentType(ContentType.JSON)
               .when()
                .body(productPojo)
               .post()
                .then();
    }
    @Step("Getting the product information with name : {0}")
    public static HashMap<String, Object> getProductInfoByName(String name) {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";
        return SerenityRest.given()
                .queryParam("name",name)
                .when()
                .get(EndPoints.GET_ALL_PRODUCT)
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);
    }
    @Step()
    public ValidatableResponse updateProduct(int productId,String name,String type,String address,String Address2,String city,double price,int shipping,String upc, String description,
                                             String manufacturer,String model, String url,String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setAddress(address);
        productPojo.setAddress2(Address2);
        productPojo.setCity(city);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("productId", productId)
                .body(productPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }
    //Get product info by Id

    @Step("Deleting product information with productId: {0}")
    public ValidatableResponse deleteStudent(int productId) {
        return SerenityRest.given()
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Updating product information with id{0}")
    public ValidatableResponse getProductById(int productId)
    {        return SerenityRest.given()
                  .pathParam("productId",productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

}