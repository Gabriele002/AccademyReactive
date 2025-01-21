package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.restConnector;

import com.intesasanpaolo.bear.connector.rest.connector.BaseRestConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryDTO;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import org.springframework.stereotype.Service;

@Service
public class CountryRestConnector extends BaseRestConnector<String, CountryResource, Integer, CountryDTO> {
}
