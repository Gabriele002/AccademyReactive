package com.intesasanpaolo.bear.mpab0.corsobearesercizi.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.service.BaseService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService extends BaseService {


    public List<CountryModel> getCountry(){
        List<CountryModel> countryModels = new ArrayList<>();

        countryModels.add(new CountryModel(1, "Italia-Italiano-Europa"));
        countryModels.add(new CountryModel(2, "Francia-Francese-Europa"));
        countryModels.add(new CountryModel(3, "Spagna-Spagnolo-Europa"));
        countryModels.add(new CountryModel(4, "Service-service-service"));
        return countryModels;
    }


    public List<CountryModel> getiCountryWithParam(Integer id, String info){
        List<CountryModel> countryModels = new ArrayList<>();

        countryModels.add(new CountryModel(1, "Italia-Italiano-Europa"));
        countryModels.add(new CountryModel(2, "Francia-Francese-Europa"));
        countryModels.add(new CountryModel(3, "Spagna-Spagnolo-Europa"));
        countryModels.add(new CountryModel(id, info));
        return countryModels;
    }
}
