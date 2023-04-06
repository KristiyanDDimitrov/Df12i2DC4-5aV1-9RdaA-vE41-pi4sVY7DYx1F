package com.example.covid19stats.controller;

import com.example.covid19stats.domain.CountryData;
import com.example.covid19stats.service.CountriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CountriesController {

    private final CountriesService countriesService;

    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    @GetMapping("/country/{countryCode}")
    public String getCountryData(@PathVariable String countryCode, Model model)
    {
        CountryData countryData = countriesService.getCountryByCountryCode(countryCode);

        model.addAttribute("id", countryData.id());
        model.addAttribute("country", countryData.countryName());
        model.addAttribute("countryCode", countryData.countryCode());
        model.addAttribute("slug", countryData.slug());
        model.addAttribute("newConfirmed", countryData.newConfirmed());
        model.addAttribute("totalConfirmed", countryData.totalConfirmed());
        model.addAttribute("newDeaths", countryData.newDeaths());
        model.addAttribute("totalDeaths", countryData.totalDeaths());
        model.addAttribute("newRecovered", countryData.newRecovered());
        model.addAttribute("totalRecovered", countryData.totalRecovered());
        model.addAttribute("date", countryData.date());

        return "country-data";
    }
}
