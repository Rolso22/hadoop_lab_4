package ru.bmstu.hadoop.labs.Contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TestPackage {
    @JsonProperty("packageId")
    public String packageId;
    @JsonProperty("jsScript")
    public String jsScript;
    @JsonProperty("functionName")
    public String fnName;
    @JsonProperty("tests")
    public ArrayList<Test> tests;

    public String getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFnName() {
        return fnName;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
