package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JpaCommand extends BaseCommand<Optional<CountryModel>> {

    @Autowired
    CountryService countryService;

    Integer id;

    public JpaCommand(Integer id) {
        this.id = id;
    }

    @Override
    protected Optional<CountryModel> doExecute() throws Exception {
        return countryService.getJpa(id);
    }

}
