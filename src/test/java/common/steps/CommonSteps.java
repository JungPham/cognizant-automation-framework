package common.steps;

import io.cucumber.java.en.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.testng.*;
import shared.BasePage;
import testUtils.*;
import utils.TestContext;

import static io.restassured.RestAssured.*;

public class CommonSteps extends BaseSteps{

    private final BasePage basePage = new BasePage();

    public CommonSteps(TestContext testContext, RestAssuredContext restAssuredContext) {
        super(testContext, restAssuredContext);
    }

    //end Region
    @Given("Setup base request specification")
    public void setupPreemptiveAuthSpec() {
        PreemptiveAuthSpec preemptiveAuthSpec = given()
                .baseUri(System.getProperty("url"))
                .auth()
                .preemptive();
        restAssuredContext.setPreemptiveAuthSpec(preemptiveAuthSpec);
    }

    //region When
    @When("User goes to Url")
    public void userGoesToUrl(){
        basePage.goToUrl();
    }

    @When("Set user credentials null")
    public void setUserCredentials() {
        String userName = "";
        String userPassword = "";
        RequestSpecification requestSpecification = restAssuredContext.getPreemptiveAuthSpec()
                .basic(userName, userPassword)
                .contentType(ContentType.JSON)
                .header("User-Agent", "RestAssured \uD83D\uDC38")
                .header("X-Requested-With", "XMLHttpRequest");
        restAssuredContext.setRequestSpecification(requestSpecification);
    }

    //region Then
    @Then("Response status should be {}")
    public void assertResponseStatusCode(String code) {
        //Arrange
        int expectedStatusCode = Integer.parseInt(code);

        //Act
        int actualStatusCode = restAssuredContext.getResponse().getStatusCode();
        //Assert
        if (actualStatusCode != 200) {
            System.out.println("Response body: " + restAssuredContext.getResponse().asString());
            System.out.println("Response status line: " + restAssuredContext.getResponse().statusLine());
        }
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Then("Response body should contain {string}")
    public void checkIfIsPresentInResponseBody(String expectedString) {
        //Act
        String actualResponseAsString = restAssuredContext.getResponse().asString();

        //Assert
        Assert.assertTrue(actualResponseAsString.contains(expectedString), "Expected string " + expectedString + " was not found!");
    }

    @Then("Response should contain key {string} with value {string}")
    public void assertResponseKeyValue(String keyPath, String expectedValue) {
        Response response = restAssuredContext.getResponse();
        JsonPath jsonPath = new JsonPath(response.asString());
        Assert.assertEquals(jsonPath.getString(keyPath), expectedValue);
    }
    //endregion
}
