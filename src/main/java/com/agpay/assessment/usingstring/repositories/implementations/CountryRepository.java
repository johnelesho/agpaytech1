package com.agpay.assessment.usingstring.repositories.implementations;

import com.agpay.assessment.usingstring.etc.ApiHelper;
import com.agpay.assessment.usingstring.exceptions.BadRequestException;
import com.agpay.assessment.usingstring.exceptions.DuplicatesIgnoredException;
import com.agpay.assessment.usingstring.exceptions.RecordExistsException;
import com.agpay.assessment.usingstring.repositories.contracts.ICountryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CountryRepository implements ICountryRepository {

    private static List<String> countries = new ArrayList<String>(
            Arrays.asList(
            "Afghanistan",    "Albania",        "Algeria",        "Andorra",        "Angola",         "Antigua",        "Argentina",      "Armenia",
            "Azerbaijan",     "Bahamas",        "Bahrain",        "Bangladesh",     "Barbados",       "Belarus",        "Iran",           "Iraq",
            "Belgium",        "Belize",         "Benin",          "Bhutan",         "Bolivia",        "Bosnia",         "Botswana",       "Ireland",
            "Brazil",         "Brunei",         "Bulgaria",       "Burkina",        "Burundi",        "Côte",           "Indonesia",      "Israel",
            "Cabo",           "Cambodia",       "Cameroon",       "Canada",         "Central",        "Chad",           "India",          "Kuwait",
            "Chile",          "China",          "Colombia",       "Comoros",        "Congo",          "Costa",          "Croatia",        "Laos",
            "Cuba",           "Cyprus",         "Czechia",        "Democratic",     "Denmark",        "Djibouti",       "Dominica",       "Kyrgyzstan",
            "Dominican",      "Ecuador",        "Egypt",          "El",             "Equatorial",     "Eritrea",        "Estonia",        "Latvia",
            "Eswatini",       "Ethiopia",       "Fiji",           "Finland",        "France",         "Gabon",          "Gambia",         "Australia",
            "Georgia",        "Germany",        "Ghana",          "Greece",         "Grenada",        "Guatemala",      "Guinea",         "Austria",
            "Guinea",         "Guyana",         "Haiti",          "Holy",           "Honduras",       "Hungary",        "Iceland",        "Uruguay",
            "Italy",          "Jamaica",        "Japan",          "Jordan",         "Kazakhstan",     "Kenya",          "Kiribati",       "Uganda",
            "Lebanon",        "Lesotho",        "Liberia",        "Libya",          "Liechtenstein",  "Lithuania",      "Luxembourg",      "Tunisia"
            ));


    @Override
    public List<String> findAll( int page, int size) {
        int fromIndex= (page * size);
        int toIndex = ((size*page)+size) > countries.size()? countries.size() : ((size*page)+size);
        if(fromIndex > countries.size()){
            throw new BadRequestException("Insufficient Records");
        }
        return countries.subList(fromIndex, toIndex).stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<String> search(String searchTerm) {
        if(ApiHelper.isEmpty(searchTerm)){
            return countries.stream().sorted().collect(Collectors.toList());
        }
        return  countries.stream().filter(country -> country.trim().toUpperCase().contains(searchTerm.trim().toUpperCase())).sorted()
                .collect(Collectors.toList());
    }


    @Override
    public String save(String dto) {
        if(countries.contains(dto)){
            throw new RecordExistsException();
        }
        else if(countries.add(dto)){
            return dto;
        }
        throw new BadRequestException("Could not add record");

    }

    @Override
    public List<String> saveAll(List<String> request) {
        List<String> toAdd= request.stream().filter(r -> !countries.contains(r.toUpperCase())).collect(Collectors.toList());
        countries.addAll(toAdd.stream().map(t->t.toUpperCase()).collect(Collectors.toList()));
        if(toAdd.size() != request.size()){
            throw new DuplicatesIgnoredException(toAdd, request.size() - toAdd.size() + " duplicates found but ignored");
        }
        return toAdd.stream().sorted().collect(Collectors.toList());
    }
}
