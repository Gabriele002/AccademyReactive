package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.Kafka;

import com.intesasanpaolo.bear.connector.jdbc.JDBCQueryType;
import com.intesasanpaolo.bear.connector.jdbc.request.JDBCRequest;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GetCountriesJdbcRequestTransformerKafka implements IJDBCRequestTransformer<String, Void> {

    @Override
    public JDBCRequest<Void> transform(String query, Object... args) {
        JDBCRequest<Void> jdbcConnectorRequest = new JDBCRequest<>();
        jdbcConnectorRequest.setQuery(query);
        jdbcConnectorRequest.setRowMapper(
                new BeanPropertyRowMapper(CountryModel.class));
        jdbcConnectorRequest.setType(JDBCQueryType.FIND);
        if (args.length > 1) {
            Object[] params = Arrays.copyOfRange(args, 1, args.length);
            for (int i = 0; i < params.length; i++) {
                if (params[i] instanceof String) {
                    params[i] = new String(params[i].toString());
                }
            }

            jdbcConnectorRequest.setParams(params);
        }
        return jdbcConnectorRequest;
    }
}
