package ru.bmstu.hadoop.labs.Contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequest {
    @JsonProperty("packageId")
    public String packageId;
    @JsonProperty("jsScript")
    public String jsScript;
    @JsonProperty("functionName")
    public String fnName;
    



}
