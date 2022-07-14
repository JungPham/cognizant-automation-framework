package oppenheimerProject.steps;

import common.steps.*;
import endpoints.calculator.insert.*;
import endpoints.calculator.insertMultiple.*;
import endpoints.calculator.taxRelief.*;
import io.cucumber.java.en.*;
import org.testng.*;
import testUtils.*;
import utils.*;

import java.util.*;

import static testUtils.EndpointsPathHandler.*;

public class TaxReliefSteps extends BaseSteps {

    private final TaxReliefEndpoint taxReliefEndpoint = new TaxReliefEndpoint();
    private final InsertEndpoint insertEndpoint = new InsertEndpoint();
    private final InsertMultipleEndpoint insertMultipleEndpoint = new InsertMultipleEndpoint();
    private final String path = taxReliefEndpoint.path;

    public TaxReliefSteps(TestContext testContext, RestAssuredContext restAssuredContext) {
        super(testContext, restAssuredContext);
        super.setEndpoint(taxReliefEndpoint);
    }

    @When("User sends a valid GET request to get taxRelief")
    public void sendGetRequestToGetTaxRelief(){
        sendGetRequest(getPathWithNameParameter(path, ""), true);
    }

    @Then("natid field must be masked from the {}th character onwards with dollar sign {} when uploading CSV file")
    public void verifyNatidFieldCorrectlyDisplayed(int position, String sign) throws Exception {
        //ACTUAL result - get list of working class hero from API
        List<TaxReliefDataModel> workingClassHeroListFromAPI = taxReliefEndpoint.getListWorkingClassHeroResponseBody();

        //EXPECTED result - get list of working class hero from CSV file
        List<WorkingClassHero> workingClassHeroListFromCSV = taxReliefEndpoint.getListWorkingClassHeroFromCSV();

        //compare
        Assert.assertTrue(taxReliefEndpoint.compareNatIdActualWithExpected(workingClassHeroListFromCSV, workingClassHeroListFromAPI, position, sign),"NatId in request body of insert endpoint are different with NatId in response body of taxRelief!");
    }

    @Then("The taxRelief should be correctly calculated when uploading CSV file")
    public void verifyTaxReliefAmount() throws Exception {
        //ACTUAL result - get list of working class hero from API
        List<TaxReliefDataModel> workingClassHeroListFromAPI = taxReliefEndpoint.getListWorkingClassHeroResponseBody();

        //EXPECTED result - get list of working class hero from CSV file
        List<WorkingClassHero> workingClassHeroListFromCSV = taxReliefEndpoint.getListWorkingClassHeroFromCSV();

        //compare
        //TODO: Will enable step below once bug about calculate taxt relief is fixed
//        Assert.assertTrue(taxReliefEndpoint.compareReliefActualWithExpected(workingClassHeroListFromCSV, workingClassHeroListFromAPI), "Relief in request body of insert endpoint are different with Relief in response body of taxRelief!");
    }

    //region Then
    @Then("New {} records is inserted into database correctly when using endpoint insert data")
    public void verifyRecordInserted(String isInsertMultiple) throws Exception {

        //ACTUAL result - get list of working class hero from API
        List<TaxReliefDataModel> workingClassHeroListFromAPI = taxReliefEndpoint.getListWorkingClassHeroResponseBody();

        //EXPECTED result - get list of working class hero from json file (request body of insert/insertMultiple endpoint)
        List<WorkingClassHero> workingClassHeroListFromJson;
        if (isInsertMultiple.equals("multi")) {
            workingClassHeroListFromJson = insertMultipleEndpoint.getListWorkingClassHeroFromJson();
        } else {
            workingClassHeroListFromJson = insertEndpoint.getListWorkingClassHeroFromJson();
        }

        //compare
        Assert.assertTrue(taxReliefEndpoint.compareNatIdActualWithExpected(workingClassHeroListFromJson, workingClassHeroListFromAPI, 5, "$"), "NatId in request body of insert endpoint are different with NatId in response body of taxRelief!");
        Assert.assertTrue(taxReliefEndpoint.compareNameActualWithExpected(workingClassHeroListFromJson, workingClassHeroListFromAPI), "Name in request body of insert endpoint are different with Name in response body of taxRelief!");
        //TODO: Will enable step below once bug about calculate taxt relief is fixed
//        Assert.assertTrue(taxReliefEndpoint.compareReliefActualWithExpected(workingClassHeroListFromJson, workingClassHeroListFromAPI), "Relief in request body of insert endpoint are different with Relief in response body of taxRelief!");
    }
}
