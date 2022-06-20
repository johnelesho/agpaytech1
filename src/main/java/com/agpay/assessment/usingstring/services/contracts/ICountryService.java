package com.agpay.assessment.usingstring.services.contracts;

import java.util.List;

public interface ICountryService {

    String addCountry(String request);
    List<String> addCountries(List<String> request);
    List<String> getAllCountries(int page, int size);
    List<String> searchAllCountryByName(String name);


}
