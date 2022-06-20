package com.agpay.assessment.usingstring.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
public class DuplicatesIgnoredException extends RuntimeException {
    private  List<String> toAdd;
    public DuplicatesIgnoredException(String message) {
        super(message);
    }

    public DuplicatesIgnoredException() {
        super("Duplicates Exists but ignored");
    }

    public DuplicatesIgnoredException(List<String> toAdd, String s) {
        super(s);
        this.toAdd = toAdd;
    }
}
