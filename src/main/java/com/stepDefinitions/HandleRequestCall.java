package com.stepDefinitions;

import com.utilities.Context;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HandleRequestCall {

    static Logger log = Logger.getLogger(HandleRequestCall.class.getName());
    static HandleRequestStructure handleRequestContextobj;



    @BeforeAll
    public static void before_or_after_all() {
        handleRequestContextobj = Context.getRequestContextThreadLocal();
        handleRequestContextobj.setRequest(RestAssured.given());
        log.info("******* Returning a requestSpecification to build request ******* ");
    }

    // Request Header
    @Given("API headers in key-value pair as")
    public void api_headers_in_key_value_pair_as(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> inputHeader = new HashMap<>(dataTable.asMap());
        handleRequestContextobj = Context.getRequestContextThreadLocal();
        handleRequestContextobj.getRequest().headers(inputHeader);

    }



    @When("user calls {string} Method on {string}")
    public void user_calls_method_on(String MethodName, String URL) {
        handleRequestContextobj = Context.getRequestContextThreadLocal();
        handleRequestContextobj.setMethodType(MethodName);
        handleRequestContextobj.setResourceURL(URL);
        log.info("******* Method type " + handleRequestContextobj.getRequest().log().method() + " *********");

    }

    @Then("user verifies status code is {string}")
    public void user_verifies_status_code_is(String string) {
        handleRequestContextobj = Context.getRequestContextThreadLocal();
        Response res = switch (handleRequestContextobj.getMethodType()) {
            case "GET" -> handleRequestContextobj.getRequest().get(handleRequestContextobj.getResourceURL());
            case "POST" -> handleRequestContextobj.getRequest().post(handleRequestContextobj.getResourceURL());
            default -> handleRequestContextobj.getRequest().delete(handleRequestContextobj.getResourceURL());
        };

        System.out.println(res.getStatusCode()+string);
    }


}
