package it.reactive.torneoDemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.dto.resource.TorneoResponse;
import it.reactive.torneoDemo.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "tornei", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class TorneoController {

    @Autowired
    TorneoService torneoService;


    @ApiOperation(value = "Creo un nuovo torneo", response = TorneoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Torneo creato con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @PostMapping
    public ResponseEntity<TorneoResponse> aggiungiTorneo(@RequestBody @Valid TorneoDTO torneoDTO) throws SQLException {
        return ResponseEntity.status(HttpStatus.CREATED).body(torneoService.createTorneo(torneoDTO));
    }


    @ApiOperation(value = "Censisco una squadra al torneo", response = TorneoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sqadra aggiunta al torneo con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @PutMapping("/addSquadraToTorneo/{idTorneo}/{idSquadra}")
    public ResponseEntity<TorneoResponse> censitaSquadraAlTorneo(@PathVariable @Min(0) @Max(10000) Integer idTorneo, @PathVariable @Min(0) @Max(10000) Integer idSquadra) throws SQLException {
        return ResponseEntity.ok(torneoService.aggiungoSquadra(idTorneo,idSquadra));
    }


    @ApiOperation(value = "restituisci tutti i tornei in modalità completa con anche la lista dei trasferimenti recuparati da TrasferMarket", response = TorneoResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tornei, squadre e traferimenti giocatori recuperato con sucesso"),
    })
    @GetMapping()
    public ResponseEntity<List<TorneoResponse>> getTorneoEndSquadre() throws SQLException {
        return ResponseEntity.ok(torneoService.getAllTorneo());
    }



    @ApiOperation(value = "Elimino il torneo con relative squadre assciare se non fanno parte di una altro torneo con relativi giocatori")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaTorneoConSquadreAndGiocatori(@PathVariable @Min(0) @Max(10000)
                                                                    @ApiParam("Id torneo")Integer id) throws SQLException {
        torneoService.removeTorneo(id);
        return ResponseEntity.noContent().build();
    }

}
