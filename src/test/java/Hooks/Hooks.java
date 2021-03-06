package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.ConfigurationReader;
import utilities.Driver;

public class Hooks {
    @Before
    public void setUp(){


    }

    //
    public static RequestSpecification spec;



    @Before( value = "@ApiAppointmentCreationPost")
    public void setupforAppointmentPost(){
        spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("base_url")).build();
    }

    @Before( value = "@ApiAppointmentCreationGet")
    public void setupforAppointmentGet(){
        spec = new RequestSpecBuilder().setBaseUri(ConfigurationReader.getProperty("base_url")).build();
    }


    @Before(order = 1, value = "@Appointment_UI")
    public void navigateToLandingPage(){

        Driver.getDriver().get(ConfigurationReader.getProperty("base_url"));

    }



    @After
    public void tearDown(Scenario scenario){

        if (scenario.isFailed()) {
            final byte[] screenshot=((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png","screenshots");
        }

//        Driver.closeDriver();

    }

}
