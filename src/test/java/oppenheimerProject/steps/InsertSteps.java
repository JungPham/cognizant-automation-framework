package oppenheimerProject.steps;

import common.steps.*;
import endpoints.calculator.insert.*;
import io.cucumber.java.en.*;
import testUtils.*;
import utils.*;

import static testUtils.EndpointsPathHandler.*;

public class InsertSteps extends BaseSteps {

    private final InsertEndpoint endpoint = new InsertEndpoint();
    private final String path = endpoint.path;

    public InsertSteps(TestContext testContext, RestAssuredContext restAssuredContext) {
        super(testContext, restAssuredContext);
        super.setEndpoint(endpoint);
    }

    //region When
    @When("User sends a valid POST request to insert")
    public void sendPostRequestToInsert() {
        sendPostRequest(getPathWithNameParameter(path, ""), endpoint.getRequestBody());
    }
    //end region
}
