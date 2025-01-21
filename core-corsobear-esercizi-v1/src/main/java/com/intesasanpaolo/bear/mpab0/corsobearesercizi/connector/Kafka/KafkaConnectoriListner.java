package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.Kafka;

import com.intesasanpaolo.bear.eventlistener.BaseEventListener;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.CountryCommandListnerKafka;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.dto.TopicDto;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryFactory;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KafkaConnectoriListner extends BaseEventListener {


    @Value("${KAFKA_TOPIC_DEMO}")
    private String TOPIC;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    CountryFactory countryFactory;

    @Override
    public void onReceived(byte[] payload, Headers headers) {
        try {
            JsonDeserializer<TopicDto> js = new JsonDeserializer<>(TopicDto.class);
            TopicDto topic = js.deserialize(TOPIC,headers, payload);
            String lingua = topic.getMessaggio();
            List<CountryModel> countryModels = beanFactory.getBean(CountryCommandListnerKafka.class, lingua).doExecute();
            List<CountryResource> countryResources = countryModels
                    .stream().map(countryResource ->
                            countryFactory.mapperModelToResource(countryResource))
                    .collect(Collectors.toList());
            countryResources.forEach(countryResource -> {
                logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<" +
                        countryResources.toString()+
                        "<<<<<<<<<<<<<<<<<<<<<<<<<");
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
