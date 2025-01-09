package it.reactive.torneoDemo.service;

import it.reactive.torneoDemo.dto.resource.Trasferimenti;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class TrasferimentiService {

    @Value("${urlTrasferimenti}")
    private String urlTrasferimenti;

    public Set<Trasferimenti> trasferimenti(String nome) {
        String url = urlTrasferimenti + nome;

        RestTemplate restTemplate = new RestTemplate();
        Set<Trasferimenti> trasferimentiSet = new HashSet<>();

        Trasferimenti[] trasferimentiArray = restTemplate.getForObject(url, Trasferimenti[].class);

        if (trasferimentiArray != null) {
            Collections.addAll(trasferimentiSet, trasferimentiArray);
        }

        return trasferimentiSet;
    }
}
