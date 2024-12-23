package it.reactive.torneoDemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.dto.in.SquadreDiGiocatoriDTO;
import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.dto.resource.ErrorResponse;
import it.reactive.torneoDemo.dto.resource.SquadraResponse;
import it.reactive.torneoDemo.service.SquadraService;
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
@RequestMapping(value = "squadre", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class SquadraController {

    @Autowired
    SquadraService squadraService;

    @ApiOperation(value = "Creao una nuova squadra", response = SquadraResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Squadra creata con successo"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server"),
            @ApiResponse(code = 550, message = "\t\n" +
                    "Il servizio va in errore con i cod:\n" +
                    "\n" +
                    "C1 in caso di squadra già censita\n" +
                    "C6 in caso di errore di validazione\n", response = ErrorResponse.class)
    })
    @PostMapping
    public ResponseEntity<SquadraResponse> salvaSquadra(@RequestBody @Valid SquadraDTO squadraDTO) throws Exception {
        SquadraResponse squadraResponse = squadraService.createSquadra(squadraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(squadraResponse);
    }


    @ApiOperation(value = "Creao una nuova squadra con la lista di giocatore", response = SquadraResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Squadra e giocatori creata con successo", response = SquadraResponse.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server"),
            @ApiResponse(code = 550, message = "La squadra gia censita", response = ErrorResponse.class)
    })
    @PostMapping("/squadreGiocatori")
    public ResponseEntity<SquadraResponse> salvaSquadraSquadraGiocatori(@RequestBody @Valid SquadreDiGiocatoriDTO squadraGiocatoreDTO) throws SQLException {
        return ResponseEntity.status(HttpStatus.CREATED).body(squadraService.aggiungoSquadraGiocatori(squadraGiocatoreDTO));
    }


    @ApiOperation(value = "restituisce la lista di squadre, con la lista completa di giocatori o meno", response = SquadraResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ricerca avvenuta con sucesso"),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore del server")
    })
    @GetMapping()
    public ResponseEntity<List<SquadraResponse>> ricercaSquadra(@RequestParam @ApiParam("Parametro che mi inizializza una lista di giocatori vuota o meno") boolean completo) throws SQLException {
        return ResponseEntity.ok(squadraService.returnSquadre(completo));
    }

    @ApiOperation(value = "Aggiungo un giocatore ad una determinata squadra e restituisco la squadra aggiornata", response = SquadraResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Squadre cercate", response = SquadraResponse.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "errore di server")})
    @PutMapping("/addGiocatore/{id}")
    public ResponseEntity<SquadraResponse> aggiungiGiocatore(@PathVariable @Min (0) @Max (10000)@ApiParam(value = "id squadra") Integer id,
                                                             @RequestBody @ApiParam(value = "giocatoreDTO", required = true) @Valid GiocatoreDTO giocatoreDTO) throws SQLException {
        SquadraResponse squadraResponse =squadraService.aggiungiGiocatore(giocatoreDTO, id);
        return ResponseEntity.ok(squadraResponse);
    }

    @ApiOperation(value = "Aggiorno una tifoseria se no ne creo una", response = SquadraResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tifoseria aggiornata o creata con sucesso", response = SquadraResponse.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "errore di server")})
    @PutMapping("/addTifoseria/{id}")
    public ResponseEntity<SquadraResponse> aggiungiTifoseria(@PathVariable @Min(0) @Max(10000) @ApiParam(value = "id squadra", required = true) Integer id,
                                                              @RequestBody @ApiParam(value = "tifoseria") @Valid TifoseriaDTO tifoseriaDTO) throws Exception {
        SquadraResponse squadraResponse = squadraService.aggiungiTifoseria(tifoseriaDTO, id);
        return ResponseEntity.ok(squadraResponse);
    }


    @ApiOperation(value = "Elimino squadra con relativi giocatori")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> rimuoviSquadra(@PathVariable @Min(0) @Max(10000)
                                                   @ApiParam("id squadra")  Integer id) throws SQLException {
        squadraService.deleteSquadra(id);
        return ResponseEntity.noContent().build();
    }

}
