package ru.bmstu.hadoop.labs.Contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Test {
    @JsonProperty("")
    public String name;
    public String result;
    public List<Integer> params;
}
