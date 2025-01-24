package com.intesasanpaolo.bear.mpab0.corsobearesercizi.controller;


import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.*;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryFactory;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/country")
public class CountryController extends CoreController {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    CountryFactory countryFactory;

    @Autowired
    CountryCommandService countryService;

    // Prima implementazione con bear
    public ResponseEntity<List<CountryResource>> getCountry() {
        List<CountryResource> countryResources = new ArrayList<>();

        countryResources.add(new CountryResource(1, "Italia", "Italiano", "Europa"));
        countryResources.add(new CountryResource(2, "Stati Uniti", "Inglese", "North America"));
        countryResources.add(new CountryResource(3, "Spagna", "Spagnolo", "Europa"));
        countryResources.add(new CountryResource(4, "Francia", "Francese", "Europa"));
        countryResources.add(new CountryResource(5, "Germania", "Tedesco", "Europa"));

        return ResponseEntity.ok(countryResources);
    }

    //Implementazione con il command
    public ResponseEntity<List<CountryResource>> getCountryCommand() throws Exception {
        List<CountryModel> countryResourceList = beanFactory.getBean(CountryCommand.class).execute();
        List<CountryResource> countryResources = countryResourceList
                .stream().map(countryResource ->
                        countryFactory.mapperModelToResource(countryResource))
                .collect(Collectors.toList());

        return ResponseEntity.ok(countryResources);
    }

    // Implemenatazione con il service
    public ResponseEntity<List<CountryResource>> getCountryService() throws Exception {
        List<CountryModel> countryResourceList = beanFactory.getBean(CountryCommandService.class).getListaCountry();
        List<CountryResource> countryResources = countryResourceList
                .stream().map(countryResource ->
                        countryFactory.mapperModelToResource(countryResource))
                .collect(Collectors.toList());

        return ResponseEntity.ok(countryResources);
    }

    // Implemenatazione con il service e il pasaggio dei parametri
    public ResponseEntity<List<CountryResource>> getCountryServiceConParametri(Integer id, String info) throws Exception {
        List<CountryModel> countryResourceList = beanFactory.getBean(CountryCommandWithParam.class, info, id).getCountry();
        List<CountryResource> countryResources = countryResourceList
                .stream().map(countryResource ->
                        countryFactory.mapperModelToResource(countryResource))
                .collect(Collectors.toList());

        return ResponseEntity.ok(countryResources);
    }

    //Implementazione con jdbc
    @GetMapping(value = "/countries")
    public ResponseEntity<List<CountryResource>> getCountryConnector() throws Exception {
        List<CountryModel> countryResourceList = beanFactory.getBean(CountryCommandJdbc.class).execute();
        List<CountryResource> countryResources = countryResourceList
                .stream().map(countryResource ->
                        countryFactory.mapperModelToResource(countryResource))
                .collect(Collectors.toList());

        return ResponseEntity.ok(countryResources);
    }

    //Implementazione jpa
    @PostMapping(value = "/{id}")
    public ResponseEntity<CountryResource> getCountryByID(@PathVariable("id") Integer id) throws Exception {
        CountryModel countryModel = beanFactory.getBean(JpaCommand.class, id).execute().orElseThrow(() -> new Exception("Country non presente"));
        CountryResource countryResource = countryFactory.mapperModelToResource(countryModel);
        return ResponseEntity.ok(countryResource);
    }
}
