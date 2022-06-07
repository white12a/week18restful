package com.herokuapp.herokuappinfo;

import com.herokuapp.herokuappInfo.CreateBookingSteps;
import com.herokuapp.testbase.TestBase;
import com.herokuapp.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class HeroKuappTest extends TestBase {
    public static String username = "admin";
    private static String password ="password123";
    private static String firstname = "bhav"+ TestUtils.getRandomValue();
    private static String lastname = "pat"+ TestUtils.getRandomValue();
    private static int totalprice =345;
    private static boolean depositpaid= true;
    private static String additonalneeds ="Coffee";
    private static int bookingId;
    private static String token;

    @Steps
    CreateBookingSteps createBookingSteps;

    @Title("This will create Authentication")
    @Test
    public void test001(){
        ValidatableResponse response=createBookingSteps.getAuthToken(username,password);
        response.log().all().statusCode(200);
        HashMap<String,?>tokenMap=response.log().all().extract().path("");
        Assert.assertThat(tokenMap,hasKey("token"));
        System.out.println(token);
    }

    @Title("This will create booking")
    @Test
    public void test002(){
        HashMap<Object,Object>bookingdata=new HashMap<>();
        bookingdata.put("check in","2022-04-04");
        bookingdata.put("check out","2022-05-05");
       ValidatableResponse response= createBookingSteps.createBooking(firstname,lastname,totalprice,depositpaid,bookingdata,additonalneeds);
       response.log().all().statusCode(200);
       bookingId=response.log().all().extract().path("bookingid");
       HashMap<String,?>map=new HashMap<>();
      Assert.assertThat(map,hasValue(firstname));
    }
    @Title("update booking by id")
    @Test
    public void test003(){
        HashMap<Object,Object>bookingdata1=new HashMap<>();
        bookingdata1.put("checkin","2018-10-10");
        bookingdata1.put("checkout","2018-12-12");
        ValidatableResponse response=createBookingSteps.updateBooking(bookingId,firstname,lastname,totalprice,depositpaid,bookingdata1,additonalneeds);
        response.log().all().statusCode(200);
    }
    @Title("This will delete booking")
    @Test
    public void test004(){
        ValidatableResponse response=createBookingSteps.deleteBooking(bookingId);
        response.log().all().statusCode(201);
        ValidatableResponse response1= createBookingSteps.getBookingById(bookingId);
        response.log().all().statusCode(404);
    }
}
