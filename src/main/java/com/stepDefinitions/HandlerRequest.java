package com.stepDefinitions;

import com.utilities.Context;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HandlerRequest {

    static Logger log=Logger.getLogger(HandlerRequest.class.getName());
    static RequestContext requestContextobj;

    @BeforeAll
    public static void before_or_after_all(){
        requestContextobj= Context.getRequestContextThreadLocal();
        requestContextobj.setRequest(RestAssured.given());
        log.info("******* Returning a requestSpecification to build request ******* ");
    }
    @Given("api headers in key-value pair as")
    public void api_headers_in_key_value_pair_as(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> inputHeader=new HashMap<>(dataTable.asMap());
        requestContextobj= Context.getRequestContextThreadLocal();
        requestContextobj.getRequest().headers(inputHeader);

    }

    @When("user calls {string} Method on {string}")
    public void user_calls_method_on(String MethodName, String URL) {
        requestContextobj= Context.getRequestContextThreadLocal();
        requestContextobj.setMethodType(MethodName);
        requestContextobj.setResourceURL(URL);
        log.info("******* Method type "+ requestContextobj.getRequest().log().method()+" *********");

    }

    @Then("user verifies status code is {string}")
    public void user_verifies_status_code_is(String string) {
        requestContextobj= Context.getRequestContextThreadLocal();
        Response res=requestContextobj.getRequest().get(requestContextobj.getResourceURL());
        System.out.println(res.getStatusCode());
    }


}
