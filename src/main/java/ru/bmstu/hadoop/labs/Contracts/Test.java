package ru.bmstu.hadoop.labs.Contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Test {
    @JsonProperty("testName")
    public String name;
    @JsonProperty("expectedResult")
    public String result;
    @JsonProperty("")
    public List<Integer> params;
}
