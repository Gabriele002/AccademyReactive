package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.controller;

import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command.CountryCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("country")
public class CountryController extends CoreController {

    @Autowired
    BeanFactory beanFactory;

    @GetMapping("/{id}")
    public ResponseEntity<CountryResource> getCountryWithIdCore(@PathVariable String id) throws Exception {
        CountryResource countryResource = beanFactory.getBean(CountryCommand.class, id).execute().orElseThrow(() -> new Exception("Country non presente"));
        return ResponseEntity.ok(countryResource);
    }
}
