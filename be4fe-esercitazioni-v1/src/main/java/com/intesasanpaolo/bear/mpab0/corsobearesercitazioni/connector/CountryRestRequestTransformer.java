package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector;

import com.intesasanpaolo.bear.connector.rest.model.RestConnectorRequest;
import com.intesasanpaolo.bear.connector.rest.transformer.IRestRequestTransformer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountryRestRequestTransformer implements IRestRequestTransformer<String, Integer> {

    @Override
    public RestConnectorRequest<Integer> transform(String voidParam, Object... args) {
        RestConnectorRequest<Integer> restConnectorRequest = new RestConnectorRequest<>();
        Map map = new HashMap<>();
        map.put("id", Integer.parseInt(voidParam));
        restConnectorRequest.setParams(map);
        return restConnectorRequest;
    }
}


