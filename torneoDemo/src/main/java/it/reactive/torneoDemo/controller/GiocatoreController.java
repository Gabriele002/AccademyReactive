package it.reactive.torneoDemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.torneoDemo.dto.resource.ErrorResponse;
import it.reactive.torneoDemo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemo.service.GiocatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;

@RestController
@Validated
@RequestMapping(value = "giocatori", produces = {MediaType.APPLICATION_JSON_VALUE})
public class GiocatoreController {

    @Autowired
    GiocatoreService giocatoreService;

    @ApiOperation(value = "Aggiorna ammonizione per un determinato giocatore", response = GiocatoreResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Aggiorna giocatore", response = GiocatoreResponse.class),
            @ApiResponse(code = 400, message = "Dati inseriti non validi"),
            @ApiResponse(code = 500, message = "Errore di server")})
            @ApiResponse(code = 550, message = "C6 in caso di valisazione fallita per l id giocatore", response = ErrorResponse.class)
    @PutMapping("/updateAmmonizioni/{id}")
    public ResponseEntity<GiocatoreResponse> aggiornaAmmonizione(@PathVariable @Min(0) @Max (10000) @ApiParam(value = "id giocatore compreso tra 0 e 10000", required = true) Integer id) throws Exception {
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<HashSet<GiocatoreResponse>> giocatore(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(giocatoreService.giocatore(id));
    }

}
