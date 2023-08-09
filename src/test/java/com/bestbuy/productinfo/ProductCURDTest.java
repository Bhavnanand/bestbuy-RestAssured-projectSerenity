package com.bestbuy.productinfo;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCURDTest extends TestBase {
    static String name = "Duracell - AAA Batteries (4-Pack)";
    static String type = "HardGood";
    static double price = 5.49;
    static String upc = "041333424019";
    static int shipping = 0;
    static String description = "Compatible with select electronic devices; AAA size; DURALOCK Power Preserve technology; 4-pack";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
     static int productId;

    @Title("This will create a new product")
    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        ;
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .when().body(productPojo)
                .post()
                .then().log().all().statusCode(201);
    }

    @Title("Verify if the product was added")
    @Test
    public void test002() {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";
        HashMap<String, Object> productMap = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCT)
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);
          Assert.assertThat(productMap, hasValue(name));
           productId = (int) productMap.get("id");
    }
    @Title("This will retrieve product with id")
    @Test
    public void test003() {

        productsteps.getProductById(productId).log().all().statusCode(200);

    }


    @Title("Update the user information and verify the updated information")
    @Test
    public void test004() {
        name = name + "_updated";
        name = "";
        productsteps.updateProduct()
                .statusCode(200);
        HashMap<String, Object> productMap = productsteps.getProductInfoByName(name).toJavaMap();
        Assert.assertThat(productMap, hasValue(name));
    }
}

