package com.agpay.assessment.usingstring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST)
public class RecordExistsException extends RuntimeException {

    public RecordExistsException(String message){
        super(message);
    }
    public RecordExistsException(){
        super("Country record already exists ");
    }
}
