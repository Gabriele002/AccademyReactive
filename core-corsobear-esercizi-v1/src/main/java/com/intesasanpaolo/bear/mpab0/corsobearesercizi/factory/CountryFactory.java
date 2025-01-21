package com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory;

import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.stereotype.Component;

@Component
public class CountryFactory {

    public CountryResource mapperModelToResource(CountryModel countryModel) {
        String[] infoCountry = countryModel.getInfo().split("-");
        CountryResource countryResource = new CountryResource();
        countryResource.setChiave(countryModel.getId());
        countryResource.setContinente(infoCountry[0]);
        countryResource.setLingua(infoCountry[1]);
        countryResource.setNome(infoCountry[2]);
        return countryResource;
    }
}
