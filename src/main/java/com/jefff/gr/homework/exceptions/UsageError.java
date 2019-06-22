package com.jefff.gr.homework.exceptions;

public enum UsageError {
    NumFieldsError("Invalid number of fields. Five non-empty delimited fields are required"),
    BirthDateError("Invalid value for BirthDate: Values should be of this form: 'M/D/YYYY'"),
    GenderError("Invalid value for Gender: Values should begin with: 'M', 'm', 'F', or 'f' ");

    private String errStr;

    UsageError(String errStr) {
        this.errStr = errStr;
    }

    public String getErrStr() {
        return errStr;
    }
}
