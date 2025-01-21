package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommand extends BaseCommand<Optional<CountryResource>> {

    @Autowired
    CountryService countryService;

    String id;

    public CountryCommand(String id) {
        this.id = id;
    }


    @Override
    protected Optional<CountryResource> doExecute() throws Exception {
        return Optional.of(countryService.getCountryById(id));
    }

}
