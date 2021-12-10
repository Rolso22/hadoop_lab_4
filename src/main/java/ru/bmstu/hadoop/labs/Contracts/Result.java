package ru.bmstu.hadoop.labs.Contracts;

public class Result {
    private final String name;
    private final String result;
    private final String packageId;

    public Result(String name, String result, String packageId) {
        this.name = name;
        this.result = result;
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public String getPackageId() {
        return packageId;
    }
}
