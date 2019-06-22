package com.jefff.gr.homework.exceptions;

public class UsageException extends RuntimeException {
    private UsageError usageError;

    public UsageException(UsageError usageError) {
        super(usageError.getErrStr());
        this.usageError = usageError;
    }

    public UsageError getUsageError() {
        return usageError;
    }
}
