package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.Kafka;


import com.intesasanpaolo.bear.event.BaseEventConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.TopicDto;
import org.springframework.stereotype.Service;

@Service
public class ConnectorKafkaMessage  extends BaseEventConnector<TopicDto, Boolean, String, Void> {
}
