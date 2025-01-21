package com.intesasanpaolo.bear.mpab0.corsobearesercizi.service;

import com.intesasanpaolo.bear.connector.jdbc.JDBCQueryType;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.jdbc.GetCountriesJdbcConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.GetCountriesJdbcRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.GetCountriesJdbcResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.jpa.JpaRepository;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.service.BaseService;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService extends BaseService {

    @Autowired
    GetCountriesJdbcConnector jdbcConnector;
    @Autowired
    private GetCountriesJdbcResponseTransformer response;
    @Autowired
    private GetCountriesJdbcRequestTransformer request;

    @Autowired
    private JpaRepository jpaService;


    public List<CountryModel> getCountry() {
        List<CountryModel> countryModels = new ArrayList<>();

        countryModels.add(new CountryModel(1, "Italia-Italiano-Europa"));
        countryModels.add(new CountryModel(2, "Francia-Francese-Europa"));
        countryModels.add(new CountryModel(3, "Spagna-Spagnolo-Europa"));
        countryModels.add(new CountryModel(4, "Service-service-service"));
        return countryModels;
    }


    public List<CountryModel> getiCountryWithParam(Integer id, String info) {
        List<CountryModel> countryModels = new ArrayList<>();

        countryModels.add(new CountryModel(1, "Italia-Italiano-Europa"));
        countryModels.add(new CountryModel(2, "Francia-Francese-Europa"));
        countryModels.add(new CountryModel(3, "Spagna-Spagnolo-Europa"));
        countryModels.add(new CountryModel(id, info));
        return countryModels;
    }


    public List<CountryModel> getJdbc() {
        List<CountryModel> getCountries =
                jdbcConnector.call("select * from countries",
                        request, response);
        return getCountries;
    }


    public Optional<CountryModel> getJpa(Integer key) {
        Optional<CountryModel> country = jpaService.findById(key);
        return country;
    }



    public List<CountryModel> countryModelsFindByLingua(String lingua) {
        String query = "select * from countries c where info like ?";
        return jdbcConnector.call(query,request,response,
                Collections.singletonList("%" + lingua + "%"),
                JDBCQueryType.FIND);
    }
}

