package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommandWithParam {

    @Autowired
    CountryService countryService;


    String info;
    Integer id;


    public CountryCommandWithParam(String info, Integer id) {
        this.info = info;
        this.id = id;
    }

    public List<CountryModel> getCountry(){
        return countryService.getiCountryWithParam(id, info);
    }
}
