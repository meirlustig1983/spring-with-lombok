package com.ml.springwithlombok.enums;

public enum EmployeeStatus {
    PART_TIME, FULL_TIME, SELF_EMPLOYED, UNKNOWN;

    public static EmployeeStatus convert(String status) {
        for (EmployeeStatus employeeStatus : EmployeeStatus.values()) {
            if (employeeStatus.toString().equalsIgnoreCase(status)) {
                return employeeStatus;
            }
        }
        return EmployeeStatus.UNKNOWN;
    }
}