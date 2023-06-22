package com.example.practice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj, Object error) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        if(responseObj == null || !responseObj.toString().equals("-999")) map.put("data", responseObj);
        if(error!=null) map.put("error", error);
        return new ResponseEntity<Object>(map,status);
    }
}
