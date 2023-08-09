package com.bestbuy.productinfo;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.RestRequests.given;

@RunWith(SerenityRunner.class)
public class StoreCRUDTest extends TestBase {
    public void createStore() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName("Minnetonka");
        storePojo.setType("BigBox");
        storePojo.setAddress("13513 Ridgedale Dr");
        storePojo.setAddress2("");
        storePojo.setCity("Hopkins");
        storePojo.setState("MN");
        storePojo.setZip("55305");
        storePojo.setLat(44.969658);
        storePojo.setLng(-93.449539);
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
        storePojo.setCreatedAt("2016-11-17T17:57:05.708Z");
        storePojo.setUpdatedAt("2016-11-17T17:57:05.708Z");

        Response response = given()
                .header("content-Type", "application/json")
                .when()
                .body(storePojo)
                .post();
        response.prettyPrint();
        response.then().statusCode(201);
    }
}
//            "type": "BigBox",
//            "address": "13513 Ridgedale Dr",
//            "address2": "",
//            "city": "Hopkins",
//            "state": "MN",
//            "zip": "55305",
//            "lat": 44.969658,
//            "lng": -93.449539,
//            "hours": "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8",
//            "createdAt": "2016-11-17T17:57:05.708Z",
//            "updatedAt": "2016-11-17T17:57:05.708Z",

