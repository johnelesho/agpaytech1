package com.agpay.assessment.usingstring.services.implementations;

import com.agpay.assessment.usingstring.repositories.contracts.ICountryRepository;
import com.agpay.assessment.usingstring.services.contracts.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public String addCountry(String request) {
        return countryRepository.save(request.trim().toUpperCase());
    }

    @Override
    public List<String> addCountries(List<String> request) {
        return countryRepository.saveAll(request);
    }

    @Override
    public List<String> getAllCountries(int page, int size) {
        return countryRepository.findAll(page, size);
    }

    @Override
    public List<String> searchAllCountryByName(String name) {
        return countryRepository.search(name);
    }
}
