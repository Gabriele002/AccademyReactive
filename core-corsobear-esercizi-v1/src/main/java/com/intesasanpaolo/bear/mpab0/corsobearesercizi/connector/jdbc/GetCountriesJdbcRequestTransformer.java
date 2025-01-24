package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.jdbc;

import com.intesasanpaolo.bear.connector.jdbc.JDBCQueryType;
import com.intesasanpaolo.bear.connector.jdbc.request.JDBCRequest;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

@Service
public class GetCountriesJdbcRequestTransformer implements IJDBCRequestTransformer<String, Void> {

    @Override
    public JDBCRequest<Void> transform(String query, Object... args) {
        JDBCRequest<Void> jdbcConnectorRequest = new JDBCRequest<>();
        jdbcConnectorRequest.setQuery(query);
        jdbcConnectorRequest.setRowMapper(
                new BeanPropertyRowMapper(CountryModel.class));
        jdbcConnectorRequest.setType(JDBCQueryType.FIND);
        return jdbcConnectorRequest;
    }
}
