package com.stepDefinitions;

import io.restassured.specification.RequestSpecification;

public class HandleRequestStructure {
    private RequestSpecification request;
    private String methodType;
    private String resourceURL;

    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

}
