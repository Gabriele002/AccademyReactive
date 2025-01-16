package com.intesasanpaolo.bear.mpab0.corsobearesercizi.controller;


import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryController extends CoreController {


    @GetMapping(value = "/countries")
    public ResponseEntity<List<CountryResource>> listaCountry() {
        List<CountryResource> countryResources = new ArrayList<>();

        countryResources.add(new CountryResource(1, "Italia", "Italiano", "Europa"));
        countryResources.add(new CountryResource(2, "Stati Uniti", "Inglese", "North America"));
        countryResources.add(new CountryResource(3, "Spagna", "Spagnolo", "Europa"));
        countryResources.add(new CountryResource(4, "Francia", "Francese", "Europa"));
        countryResources.add(new CountryResource(5, "Germania", "Tedesco", "Europa"));
        
        return ResponseEntity.ok(countryResources);
    }
}
