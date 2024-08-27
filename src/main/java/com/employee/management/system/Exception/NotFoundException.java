package com.employee.management.system.Exception;
import  java.util.*;
import com.employee.management.system.Utility.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundException extends RuntimeException {

    public NotFoundException()
    {
        super("Resource not found on server");
    }

    public NotFoundException(String message)
    {
        super(message);
    }
    @ExceptionHandler(value= NotFoundException.class)
    public ResponseEntity<Object> exceptionHandler()
    {
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"Employee id is not found",null);

    }
    @ExceptionHandler(value={ArithmeticException.class,NullPointerException.class})
    public ResponseEntity<Object> exceptionHandler2()
    {
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"null pointer exception occurred",null);

    }


    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object>handledArgumentException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String,String>map=new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error)->{
            String feildName=((FieldError)error).getField();
    String feildErrorMessage=((FieldError)error).getDefaultMessage();
    map.put(feildName,feildErrorMessage);
});
return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"client side error",map);
    }


    @ExceptionHandler(value= Exception.class)
    public ResponseEntity<Object> exceptionHandler3()
    {
        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,false,"Exception occurred",null);

    }


}
