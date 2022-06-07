package com.herokuapp.herokuappInfo;

import com.herokuapp.constants.EndPoints;
import com.herokuapp.model.HeroKuAppPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CreateBookingSteps {

    @Step("Create auth username : {0},password : {1}")
    public ValidatableResponse getAuthToken(String username, String password) {
        HeroKuAppPojo heroKuAppPojo = new HeroKuAppPojo();
        heroKuAppPojo.setUsername(username);
        heroKuAppPojo.setPassword(password);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(heroKuAppPojo)
                .when()
                .post(EndPoints.CREATE_AUTH)
                .then();

    }

    @Step("Create Booking  firstname: {0}, lastname: {1}, totalprice: {2}, depositepaid: {3}, bookingsDates:{4}, addtionalsneeds : {5} ")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, boolean depositpaid, HashMap<Object, Object> bookingdates, String additionalneeds) {
        HeroKuAppPojo heroKuAppPojo = new HeroKuAppPojo();
        heroKuAppPojo.setFirstname(firstname);
        heroKuAppPojo.setLastname(lastname);
        heroKuAppPojo.setTotalprice(totalprice);
        heroKuAppPojo.setDepositpaid(depositpaid);
        heroKuAppPojo.setBookingdates(bookingdates);
        heroKuAppPojo.setAdditionalneeds(additionalneeds);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(heroKuAppPojo)
                .when()
                .post(EndPoints.GET_BOOKING)
                .then();
    }

    @Step("Update Booking  firstname: {0}, lastname: {1}, totalprice: {2}, depositepaid: {3}, bookingsDates:{4}, addtionalsneeds : {5} ")
    public ValidatableResponse updateBooking(int bookingid, String firstname, String lastname, int totalprice, boolean depositpaid, HashMap<Object, Object> bookingdates, String additionalneeds) {
        HeroKuAppPojo heroKuAppPojo = new HeroKuAppPojo();
        heroKuAppPojo.setFirstname(firstname);
        heroKuAppPojo.setLastname(lastname);
        heroKuAppPojo.setTotalprice(totalprice);
        heroKuAppPojo.setDepositpaid(depositpaid);
        heroKuAppPojo.setBookingdates(bookingdates);
        heroKuAppPojo.setAdditionalneeds(additionalneeds);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(heroKuAppPojo)
                .pathParam("bookingId", bookingid)
                .auth().basic("admin", "password123")
                .when()
                .post(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }
    @Step("Delete booking by bookingId : {0}")
    public ValidatableResponse deleteBooking(int bookingId){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("bookingId",bookingId)
                .auth().basic("admin","password123")
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }
    @Step("Get booking by bookingId : {0}")
    public ValidatableResponse getBookingById(int bookingId){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("bookingId",bookingId)
                .when()
                .get(EndPoints.GET_BOOKING_BY_ID)
                .then();
    }
}