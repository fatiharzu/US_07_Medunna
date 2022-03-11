package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.AppointmentGet;
import utilities.ConfigurationReader;
import java.util.HashMap;
import java.util.Map;
import static Hooks.Hooks.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.ApiUtils.getRequest;
import static utilities.Authentication.generateToken;
import static utilities.WriteToTxt.saveAppointmentCreation;

public class US_07_API_AppointmentStepDefs {


    AppointmentGet appointmentCreate = new AppointmentGet();
    Response response;


    @Given("user sets the necessary path params for appointment creation")
    public void user_sets_the_necessary_path_params_for_appointment_creation() {
        spec.pathParams("first", "api", "second", "appointments","third","request");
    }



    @Given("user sets the expected data {string}, {string} {string} {string} {string}  and {string}")
    public void user_sets_the_expected_data_and(String firstname, String lastname, String SSN, String email, String phone, String date) {
        appointmentCreate.setFirstName(firstname);
        appointmentCreate.setLastName(lastname);
        appointmentCreate.setSsn(SSN);
        appointmentCreate.setEmail(email);
        appointmentCreate.setPhone(phone);
        appointmentCreate.setStartDate(date);
    }

    @Given("user sends the POST request and gets the response for appointment creation")
    public void user_sends_the_post_request_and_gets_the_response_for_appointment_creation() {
        response = given().spec(spec).contentType(ContentType.JSON).body(appointmentCreate).when().post("/{first}/{second}/{third}");
    }



    @When("user saves the appointment records to correspondent files")
    public void user_saves_the_appointment_records_to_correspondent_files() {
        saveAppointmentCreation(appointmentCreate);
    }


    @Then("user validates appointment records")
    public void user_validates_appointment_records() throws Exception {
        response.then().statusCode(201);
        response.prettyPrint();


        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(appointmentCreate.getFirstName(),((Map)actualData.get("patient")).get("firstName"));
       assertEquals(appointmentCreate.getSsn(),((Map)((Map)actualData.get("patient")).get("user")).get("ssn"));

    }


    //GET REQUEST
    @Given("user sends the get request for appointments data")
    public void user_sends_the_get_request_for_appointments_data() {
        response = getRequest(generateToken(),ConfigurationReader.getProperty("appointments_endpoint"));


    }


    @Given("user deserializes data to Java for appointments")
    public void user_deserializes_data_to_java_for_appointments() {
        response.prettyPrint();
    }


    @Given("user saves the users data to correspondent files for appointments")
    public void user_saves_the_users_data_to_correspondent_files_for_appointments() {
        saveAppointmentCreation(appointmentCreate);
    }
























}
