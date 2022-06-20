package com.agpay.assessment.usingstring.controllers;

import com.agpay.assessment.usingstring.dtos.ApiResponse;
import com.agpay.assessment.usingstring.etc.ApiHelper;
import com.agpay.assessment.usingstring.exceptions.DuplicatesIgnoredException;
import com.agpay.assessment.usingstring.services.contracts.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private ICountryService countryService;

    @GetMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getAllCountries(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size){
        List<String> responseData= new ArrayList<>();
        try {
           responseData = countryService.getAllCountries(page, size);
                return new ResponseEntity<>(new ApiResponse(responseData, HttpStatus.OK, "Success"), HttpStatus.OK);

        }
        catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(responseData, HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value="/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getCountryByName(@RequestParam("keyword") String name){
        List<String> responseData = null;
        try {
            responseData = countryService.searchAllCountryByName(name);
            if(ApiHelper.isEmpty(responseData)){
                return new ResponseEntity<>(new ApiResponse(responseData, HttpStatus.NO_CONTENT, "Empty"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(responseData, HttpStatus.OK, "Success"), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(responseData, HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
        }

    }


    @PostMapping(value="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addNewCountry(@RequestParam("name") String request){
        String added = null;
        try{
            added = countryService.addCountry(request);
            return new ResponseEntity<>(new ApiResponse(added, HttpStatus.CREATED, "Success"), HttpStatus.CREATED);

        }
        catch (Exception ex){
            return new ResponseEntity<>(new ApiResponse(added, HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value="/many", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addNewCountries(@RequestParam("name") List<String> request){
        List<String> responseData = null;
        try{
            responseData = countryService.addCountries(request);
            return new ResponseEntity<>(new ApiResponse(responseData, HttpStatus.CREATED, "Success"), HttpStatus.CREATED);
        }
        catch (DuplicatesIgnoredException ex){
            return new ResponseEntity<>(new ApiResponse(ex.getToAdd(), HttpStatus.OK, ex.getMessage()), HttpStatus.OK);
        }
    }


}
