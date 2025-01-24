package it.reactive.torneoDemoMongo.service;

import it.reactive.torneoDemoMongo.dto.resource.Trasferimenti;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TrasferimentiService {



    public List<Trasferimenti> trasferimenti(String nome) {
        String url =  "http://85.235.148.177:8872/transfer/" + nome;
        RestTemplate restTemplate = new RestTemplate();
        List<Trasferimenti> trasferimentiSet = new ArrayList<>();
        Trasferimenti[] trasferimentiArray = restTemplate.getForObject(url, Trasferimenti[].class);
        if (trasferimentiArray != null) {
            Collections.addAll(trasferimentiSet, trasferimentiArray);
        }
        return trasferimentiSet;
    }
}
