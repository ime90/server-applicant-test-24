package com.freenow.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This driver doesn't have a car")
public class DriverHasNoCarException extends  Exception{
    static final long serialVersionUID = -3387516993334229948L;


    public DriverHasNoCarException(String message)
    {
        super(message);
    }

}
