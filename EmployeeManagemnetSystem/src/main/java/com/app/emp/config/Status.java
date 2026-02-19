package com.app.emp.config;

public enum Status {
    INACTIVE(0, "Inactive"),
    ACTIVE(1, "Active");

    private final int code;
    private final String description;

    private Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Status fromCode(int code) {
        for (Status status : Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }
}

