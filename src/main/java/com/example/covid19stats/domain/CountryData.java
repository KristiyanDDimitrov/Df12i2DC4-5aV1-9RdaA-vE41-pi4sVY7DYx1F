package com.example.covid19stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryData(String id,
                          String countryName,
                          String countryCode,
                          String slug,
                          int newConfirmed,
                          int totalConfirmed,
                          int newDeaths,
                          int totalDeaths,
                          int newRecovered,
                          int totalRecovered,
                          LocalDateTime date) {
}
