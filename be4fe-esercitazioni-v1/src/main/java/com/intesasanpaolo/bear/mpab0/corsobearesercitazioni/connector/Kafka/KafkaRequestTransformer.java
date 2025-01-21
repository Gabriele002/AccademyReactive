package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.Kafka;

import com.intesasanpaolo.bear.event.request.EventRequest;
import com.intesasanpaolo.bear.event.transformer.IEventRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.TopicDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;



@Service
public class KafkaRequestTransformer implements IEventRequestTransformer<TopicDto, String> {
    @Value("${KAFKA_TOPIC_DEMO}")
    private String TOPIC;

    @Override
    public EventRequest<String> transform(TopicDto om, Object... args) {
        EventRequest<String> event = new EventRequest<>();
        JsonSerializer<TopicDto> jsonSerializer = new JsonSerializer<>();
        event.setTopic(TOPIC);
        event.setPayload(jsonSerializer.serialize(TOPIC, om));
        jsonSerializer.close();
        return event;
    }
}
