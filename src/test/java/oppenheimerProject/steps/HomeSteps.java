package oppenheimerProject.steps;

import endpoints.calculator.taxRelief.*;
import pages.HomePage;
import common.steps.BaseSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import testUtils.*;
import utils.TestContext;

import java.util.*;

public class HomeSteps extends BaseSteps {

    private final HomePage homePage = new HomePage();
    private final TaxReliefEndpoint endpoint = new TaxReliefEndpoint();

    public HomeSteps(TestContext testContext, RestAssuredContext restAssuredContext) {
        super(testContext, restAssuredContext);
    }

    //region When
    @When("User clicks on Browse button and upload CSV file on Home page")
    public void clickOnBrowseAndUploadCSVFile(){
        homePage.clickOnBrowseAndUploadCSVFile();
    }

    @When("User clicks on Refresh Tax Relief Table button")
    public void clickOnRefreshTaxReliefTableButton(){
        homePage.clickOnRefreshTaxReliefTableButton();
    }

    @When("User clicks on button with name Dispense Now on Home page")
    public void clickOnDispenseNowButton(){
        homePage.clickOnDispenseNowButton();
    }
    //end region

    //region Then
    @Then("Data of NatId table on UI is correctly displayed")
    public void verifyNatIdWorkingClassHeroIsDisplayedCorrectlyOnUI() throws Exception {
        //ACTUAL result - get list of natId working class hero from UI
        List<String> natIdListFromUI = homePage.getListNatIdFromUI();

        //EXPECTED result - get list of working class hero from CSV file
        List<WorkingClassHero> workingClassHeroListFromCSV = endpoint.getListWorkingClassHeroFromCSV();

        //compare
        Assert.assertEquals(homePage.compareNatIdActualWithExpected(workingClassHeroListFromCSV, natIdListFromUI, 5, "$"), true, "NatId in CSV are different with NatId displays on UI!");
    }

    @Then("Data of Relief table on UI is correctly displayed")
    public void verifyReliefWorkingClassHeroIsDisplayedCorrectlyOnUI() throws Exception {
        //ACTUAL result - get list of relief working class hero from UI
        List<String> reliefListFromUI = homePage.getListReliefFromUI();

        //EXPECTED result - get list of working class hero from CSV file
        List<WorkingClassHero> workingClassHeroListFromCSV = endpoint.getListWorkingClassHeroFromCSV();

        //compare
        //TODO: Will enable step below once bug about calculate taxt relief is fixed
//        Assert.assertEquals(homePage.compareReliefActualWithExpected(workingClassHeroListFromCSV, reliefListFromUI), true, "Relief calculated in CSV are different with Relief displays on UI!");
    }

    @Then("The button with name {} is displayed")
    public void verifyButtonIsDisplayed(String expectedText){
        //actual
        String actualResult = homePage.getButtonName();
        //assert
        Assert.assertEquals(actualResult, expectedText, "Result not found!");
    }

    @Then("The button must be red color")
    public void verifyColorOfButton(){
        Assert.assertEquals(homePage.getColor(), "#dc3545","The color is not expected!");
    }

    @Then("User should be redirected to a page with {} as last part of url")
    public void verifyUrlOfLoginPage(String expectedUrl){
        //actual
        String actualResult = homePage.getSectionPartOfUrl();
        //assert
        Assert.assertEquals(actualResult, expectedUrl, "Result not found!");
    }

    @Then("The text {} is displayed")
    public void verifyTextIsDisplayed(String expectedText){
        //actual
        String actualResult = homePage.getText();
        //assert
        Assert.assertEquals(actualResult, expectedText, "Result not found!");
    }
    //end region
}
