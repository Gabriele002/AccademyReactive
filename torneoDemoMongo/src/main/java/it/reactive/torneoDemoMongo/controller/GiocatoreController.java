package it.reactive.torneoDemoMongo.controller;

import io.swagger.annotations.ApiOperation;
import it.reactive.torneoDemoMongo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemoMongo.service.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("giocatore")
public class GiocatoreController {

    @Autowired
    SquadraService squadraService;


    @ApiOperation(value = "Aggiorna ammonizione per un determinato giocatore", response = Void.class)
    @PutMapping("{nomeCognome}")
    public ResponseEntity<GiocatoreResponse> aggiornaAmmonizione(@PathVariable String nomeCognome) throws Exception {
        return ResponseEntity.ok(squadraService.aggiornoAmmonizioneGiocatore(nomeCognome));
    }

}
