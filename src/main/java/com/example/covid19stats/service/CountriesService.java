package com.example.covid19stats.service;

import com.example.covid19stats.domain.CountryData;
import com.example.covid19stats.repository.CountryDataDAO;
import jakarta.annotation.PostConstruct;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountriesService {

    private final static String URL = "https://api.covid19api.com/summary";
    private final CountryDataDAO countryDataDAO;

    public CountriesService(CountryDataDAO countryDataDAO) {
        this.countryDataDAO = countryDataDAO;
    }

    @PostConstruct
    private void updateDataInRepository()
    {
        countryDataDAO.clearTable();

        List<CountryData> countriesData = getCountriesDataFromAPI();

        countriesData.forEach(countryDataDAO::insertCountryData);
    }

    private List<CountryData> getCountriesDataFromAPI()
    {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {});

        Map<String, Object> responseBody = response.getBody();

        List<Map<String, Object>> countries = (List<Map<String, Object>>) responseBody.get("Countries");

        return countries.stream().map(this::generateCountryDataFromMap).collect(Collectors.toList());
    }

    private CountryData generateCountryDataFromMap(Map<String, Object> country)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime dateTime = LocalDateTime.parse(country.get("Date").toString(), formatter);

        return new CountryData(
                country.get("ID").toString(),
                country.get("Country").toString(),
                country.get("CountryCode").toString(),
                country.get("Slug").toString(),
                Integer.parseInt(country.get("NewConfirmed").toString()),
                Integer.parseInt(country.get("TotalConfirmed").toString()),
                Integer.parseInt(country.get("NewDeaths").toString()),
                Integer.parseInt(country.get("TotalDeaths").toString()),
                Integer.parseInt(country.get("NewRecovered").toString()),
                Integer.parseInt(country.get("TotalRecovered").toString()),
                dateTime);
    }

    public CountryData getCountryByCountryCode(String countryCode)
    {
        return countryDataDAO.getCountryByCountryCode(countryCode);
    }
}
