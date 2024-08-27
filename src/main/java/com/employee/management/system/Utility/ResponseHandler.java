package com.employee.management.system.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
public class ResponseHandler {
    // humne ek Factory method create kiya Iss method ko jo bhi service call karegi vo parameter pass karke aapna response get kar sakti hai
    public static ResponseEntity<Object>generateResponse(HttpStatus httpStatus,boolean isError, String msg, Object responseBody)
    {
       Map<String,Object>m=new HashMap<>();
       try {
           m.put("status", httpStatus);
           m.put("isSuccess", isError);
           m.put("message", msg);
           m.put("data", responseBody);
           return new ResponseEntity<Object>(m, httpStatus);
       }
       catch (Exception ex){
           m.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
           m.put("isSuccess", false);
           m.put("message", ex.getMessage());
           m.put("data", null);
           return new ResponseEntity<Object>(m, httpStatus);
       }


    }
}
