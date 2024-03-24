package com.stepDefinitions;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.utilities.Context;
import io.cucumber.java.en.And;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class HandlingJsonRequestPayload {

    static DocumentContext UpdatedJsonPayload;

    public Configuration config=Configuration.builder()
            .jsonProvider(new JacksonJsonNodeJsonProvider())
            .mappingProvider(new JacksonMappingProvider())
            .build();



    // Request Body
    @And("Request json payload as String {string}")
    public void request_json_payload_as_string(String RequestPayload) {
        Context.getRequestContextThreadLocal().getRequest().body(RequestPayload);
        UpdatedJsonPayload= JsonPath.using(config).parse(RequestPayload);

    }

    @And("Request json payload as File from filePath {string}")
    public void request_json_payload_as_file_from_file_path(String FilePath) throws IOException {
        File RequestPayloadAsFile= new File(System.getProperty("user.dir"),FilePath);
        Context.getRequestContextThreadLocal().getRequest().body(RequestPayloadAsFile);
        UpdatedJsonPayload= JsonPath.using(config).parse(Files.readString(Path.of(System.getProperty("user.dir")+FilePath)));
    }

    @And("update json property {string}")
    public void update_json_property(String property, io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> toBeUpdatedValue=dataTable.asMap(String.class,String.class);
        for(Map.Entry<String,String > i:toBeUpdatedValue.entrySet()){
            if (property.isEmpty())
                UpdatedJsonPayload.set(i.getKey(),i.getValue());
            else
                UpdatedJsonPayload.set(property+"."+i.getKey(),i.getValue());
        }

        Context.getRequestContextThreadLocal().getRequest().body(UpdatedJsonPayload.jsonString());
    }
}
