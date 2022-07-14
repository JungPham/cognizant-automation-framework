package oppenheimerProject.steps;

import common.steps.*;
import endpoints.calculator.insertMultiple.*;
import io.cucumber.java.en.*;
import testUtils.*;
import utils.*;

import static testUtils.EndpointsPathHandler.*;

public class InsertMultipleSteps extends BaseSteps {

    private final InsertMultipleEndpoint endpoint = new InsertMultipleEndpoint();
    private final String path = endpoint.path;

    public InsertMultipleSteps(TestContext testContext, RestAssuredContext restAssuredContext) {
        super(testContext, restAssuredContext);
        super.setEndpoint(endpoint);
    }

    //region When
    @When("User sends a valid POST request to insertMultiple")
    public void sendPostRequestToInsertMultipleSteps() {
        sendPostRequest(getPathWithNameParameter(path, ""), endpoint.getRequestBody(true));
    }
}
