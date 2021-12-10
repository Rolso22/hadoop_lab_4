package ru.bmstu.hadoop.labs.Contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Test {
    @JsonProperty("functionName")
    public String name;
    @JsonProperty("")
    public String result;
    public List<Integer> params;
}
