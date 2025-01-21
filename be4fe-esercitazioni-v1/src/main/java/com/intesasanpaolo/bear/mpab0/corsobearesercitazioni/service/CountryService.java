package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.CountryRestRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.CountryRestResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.restConnector.CountryRestConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import com.intesasanpaolo.bear.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountryService extends BaseService {

    @Autowired
    CountryRestConnector countryRestConnector;

    @Autowired
    CountryRestResponseTransformer countryRestResponseTransformer;

    @Autowired
    CountryRestRequestTransformer countryRestRequestTransformer;

    public CountryResource getCountryById(String countryId) {
        return countryRestConnector.call(
                countryId,
                countryRestRequestTransformer,
                countryRestResponseTransformer
        );
    }
}
