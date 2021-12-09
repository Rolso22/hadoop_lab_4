package ru.bmstu.hadoop.labs.Contracts;

public class GetRequest {
    private final String packageId;

    public GetRequest(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }
}
