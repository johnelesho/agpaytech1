package com.agpay.assessment.usingstring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, value = HttpStatus.EXPECTATION_FAILED)
public class UnableToUpdateCountryException extends RuntimeException {
    public UnableToUpdateCountryException(String message) {
        super(message);
    }

    public UnableToUpdateCountryException() {
        super("Error Updating Country");
    }
}
