package ru.bmstu.hadoop.labs.Contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class Response {
    @JsonProperty("Name")
    public String name;
    @JsonProperty("Result")
    public HashMap<String, String> result;

    public Response(String name, HashMap<String, String> result) {
        this.name = name;
        this.result = result;
    }
}
