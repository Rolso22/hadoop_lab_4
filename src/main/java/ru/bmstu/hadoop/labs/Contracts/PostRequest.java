package ru.bmstu.hadoop.labs.Contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequest {
    @JsonProperty("packageId")
    private final String packageId;

}
