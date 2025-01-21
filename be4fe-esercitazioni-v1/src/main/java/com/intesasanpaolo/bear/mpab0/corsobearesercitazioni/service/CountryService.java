package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.CountryRestRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.CountryRestResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.Kafka.ConnectorKafkaMessage;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.Kafka.KafakaEventResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.Kafka.KafkaRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.restConnector.CountryRestConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.TopicDto;
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

    @Autowired
    ConnectorKafkaMessage connectorKafkaMessage;
    @Autowired
    private KafkaRequestTransformer kafkaRequestTransformer;
    @Autowired
    private KafakaEventResponseTransformer kafakaEventResponseTransformer;

    public CountryResource getCountryById(String countryId) {
        return countryRestConnector.call(
                countryId,
                countryRestRequestTransformer,
                countryRestResponseTransformer
        );
    }

    public void topicKafka(TopicDto provaMessaggioDTO){
        connectorKafkaMessage.call(provaMessaggioDTO,
                kafkaRequestTransformer, kafakaEventResponseTransformer);
    }

}
