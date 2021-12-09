package ru.bmstu.hadoop.labs.Contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequest {
    @JsonProperty()
    private final String packageId;

}
