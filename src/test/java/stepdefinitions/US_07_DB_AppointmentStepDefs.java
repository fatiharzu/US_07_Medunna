package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import utilities.DatabaseUtility;

import java.util.ArrayList;
import java.util.List;

import static com.sun.xml.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;

public class US_07_DB_AppointmentStepDefs {

    @Given("user connects to the database")
    public void user_connects_to_the_database() {
//        DatabaseUtility.createConnection();
    }
    @Given("user selects all firstname column data")
    public void user_selects_all_firstname_column_data() {

    }
    @Then("user verify {string} with the database")
    public void user_verify_with_the_database(String firstname) {
        List<Object> allColumnDAta = DatabaseUtility.getColumnData("select * from jhi_user","firstname");
        System.out.println(allColumnDAta);
        List<Object> expectedData = new ArrayList<>();
        expectedData.add(firstname);

        Assert.assertTrue(allColumnDAta.containsAll(expectedData));
    }
    @Then("close the database connection")
    public void close_the_database_connection() {
       DatabaseUtility.closeConnection();
    }
}
