package oppenheimerProject.steps;

import common.steps.*;
import endpoints.calculator.rakeDatabase.*;
import io.cucumber.java.en.*;
import testUtils.*;
import utils.*;

import static testUtils.EndpointsPathHandler.*;

public class RakeDatabaseSteps extends BaseSteps {

    private final RakeDatabaseEndpoint endpoint = new RakeDatabaseEndpoint();
    private final String path = endpoint.path;

    public RakeDatabaseSteps(TestContext testContext, RestAssuredContext restAssuredContext) {
        super(testContext, restAssuredContext);
        super.setEndpoint(endpoint);
    }

    //region When
    @Given("Clean database")
    public void cleanDatabase(){
        sendPostRequest(getPathWithNameParameter(path, ""), "");
    }
}
