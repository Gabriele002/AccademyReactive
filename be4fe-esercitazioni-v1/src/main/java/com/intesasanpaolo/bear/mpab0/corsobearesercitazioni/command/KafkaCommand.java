package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.TopicDto;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class KafkaCommand extends BaseCommand<Void> {

    @Autowired
    CountryService service;

    private TopicDto topicDto;

    public KafkaCommand(TopicDto topicDto) {
        this.topicDto = topicDto;
    }

    @Override
    protected Void doExecute() throws Exception {
        service.topicKafka(topicDto);
        return null;
    }

}

