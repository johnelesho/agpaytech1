package com.agpay.assessment.usingstring.repositories.contracts;


import java.util.List;

public interface ICountryRepository {
    List<String> findAll(int page, int size);
    List<String> search(String partialName);

    String save(String dto);

    List<String> saveAll(List<String> request);

}
