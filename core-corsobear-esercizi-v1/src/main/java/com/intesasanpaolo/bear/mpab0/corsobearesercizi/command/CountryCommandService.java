package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommandService {

    @Autowired
    CountryService countryService;

    @Autowired
    BeanFactory beanFactory;


    public List<CountryModel> getListaCountry() throws Exception {
        return countryService.getCountry();
    }
}
