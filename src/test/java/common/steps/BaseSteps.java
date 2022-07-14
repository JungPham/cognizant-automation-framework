package common.steps;

import shared.IEndpoint;
import utils.TestContext;
import testUtils.RestAssuredContext;
import io.restassured.response.Response;

public class BaseSteps {

    protected final TestContext testContext;
    protected final RestAssuredContext restAssuredContext;
    private IEndpoint endpoint;

    public void setEndpoint(IEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public BaseSteps(TestContext testContext, RestAssuredContext restAssuredContext){
        this.testContext = testContext;
        this.restAssuredContext = restAssuredContext;
    }

    //region GET
    protected void sendGetRequest(String path, boolean isResponseConvertible) {
        getRequest(path, isResponseConvertible);
    }

    private void getRequest(String path, boolean isResponseConvertible) {
        Response response = restAssuredContext.getRequestSpecification()
                .when()
                .get(path)
                .then()
                .extract()
                .response();
        restAssuredContext.setResponse(response);
        if (response.getStatusCode() == 200 && isResponseConvertible) {
            endpoint.convertResponseToDataModel(response.asString());
        }
    }
    //endregion

    //region POST
    protected void sendPostRequest(String path, String requestBody, boolean isResponseConvertible) {
        postRequest(path, requestBody, isResponseConvertible);
    }

    protected void sendPostRequest(String path, String requestBody) {
        postRequest(path, requestBody, false);
    }

    private void postRequest(String path, String requestBody, boolean isResponseConvertible) {
        Response response = restAssuredContext.getRequestSpecification()
                .when()
                .body(requestBody).log().body()
                .post(path)
                .then().extract().response();
        restAssuredContext.setResponse(response);
        if (response.getStatusCode() == 201 && isResponseConvertible) {
            endpoint.convertResponseToDataModel(response.asString());
        }
    }

    //region PUT
    protected void sendPutRequest(String path, String requestBody, boolean isResponseConvertible) {
        putRequest(path, requestBody, isResponseConvertible);
    }

    private void putRequest(String path, String requestBody, boolean isResponseConvertible) {
        Response response = restAssuredContext.getRequestSpecification()
                .when()
                .body(requestBody).log().body()
                .put(path)
                .then().extract().response();
        restAssuredContext.setResponse(response);
        if (response.getStatusCode() == 200 && isResponseConvertible) {
            endpoint.convertResponseToDataModel(response.asString());
        }
    }

    //region DELETE
    protected void sendDeleteRequest(String path) {
        deleteRequest(path, false);
    }

    public void deleteRequest(String path, boolean isResponseConvertible) {
        Response response = restAssuredContext.getRequestSpecification()
                .when()
                .delete(path)
                .then().extract().response();
        restAssuredContext.setResponse(response);
        if (response.getStatusCode() == 200 && isResponseConvertible) {
            endpoint.convertResponseToDataModel(response.asString());
        }
    }
}
