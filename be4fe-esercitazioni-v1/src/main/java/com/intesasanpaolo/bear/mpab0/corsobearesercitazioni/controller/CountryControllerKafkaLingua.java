package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.controller;

import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command.KafkaCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.TopicDto;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("countryasync")
public class CountryControllerKafkaLingua extends CoreController {

    @Autowired
    private BeanFactory beanFactory;

    @GetMapping(value = "/lingua")
    public ResponseEntity<String> produciKafka(@RequestParam String messaggio) throws BeansException, Exception {
        beanFactory.getBean(KafkaCommand.class, new TopicDto(messaggio)).execute();
        return ResponseEntity.ok("OK");
    }
}

