package it.reactive.carSharing.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.reactive.carSharing.dto.*;
import it.reactive.carSharing.dto.enumDto.EnumOrdinamento;
import it.reactive.carSharing.dto.enumDto.EnumRatingAutista;
import it.reactive.carSharing.dto.enumDto.EnumTipoUtente;
import it.reactive.carSharing.exception.ErrorResponse;
import it.reactive.carSharing.response.Token;
import it.reactive.carSharing.response.ViaggioResponse;
import it.reactive.carSharing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("carsharing")
public class CarSharingController {

    @Autowired
    RegistrazioneService registraUtente;

    @Autowired
    TokenService tokenService;

    @Autowired
    LoginService loginService;

    @Autowired
    AutistaService autistaService;

    @Autowired
    ViaggioService viaggioService;

    @ApiOperation(value = "Registra un nuovo utente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utente registrato correttamente"),
            @ApiResponse(code = 401, message = "Dati inseriti non validi", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Errore di server")})
    @PostMapping("/registrazione")
    public ResponseEntity<HttpStatus> registraUtente(@RequestBody @Valid UtenteRegisterDto utenteDTO, EnumTipoUtente tipoUtente) {
        registraUtente.registrazione(utenteDTO, tipoUtente);
        return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.resolve(200));
    }

    @ApiOperation(value = "Accesso tramite email e psw")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utente loggato correttamente"),
            @ApiResponse(code = 401, message = "Dati inseriti non validi", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Errore di server")})
    @PostMapping("/loggin")
    public ResponseEntity<String> logginUtente(@RequestBody LogginDto logginDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("token", loginService.login(logginDto))
                .body(loginService.login(logginDto));
    }

    @ApiOperation(value = "Controlla se il token e valido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Verifica avvenuta correttamente"),
            @ApiResponse(code = 401, message = "Dati inseriti non validi", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Errore di server")})
    @PostMapping("/verificaToken")
    public ResponseEntity<String> verificaToken(@RequestHeader(value = "token") String token) {
        Token tokenValid = tokenService.isTokenValid(token);
        System.out.println("tokenValid = " + tokenValid);
        return ResponseEntity.status(HttpStatus.CREATED).body("Token valido");
    }


    @ApiOperation(value = "Creo un nuovo viaggio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Viaggio creato correttamente"),
            @ApiResponse(code = 401, message = "Dati inseriti non validi", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Errore di validazione Token", response = ErrorResponse.class)})
    @PostMapping("/creaviaggio")
    public ResponseEntity<ViaggioResponse> creaViaggio(@RequestHeader(value = "token") String token, @Valid @RequestBody CreaViaggioDto creaViaggioDto) {
        ViaggioResponse viaggioResponse = autistaService.creaViaggioDto(creaViaggioDto, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(viaggioResponse);
    }


    @ApiOperation(value = "Lista viaggi")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Viaggio ritornati con sucesso"),
    })
    @PostMapping("/restituisciViaggi")
    public ResponseEntity<List<ViaggioResponse>> getViaggi(@RequestHeader(value = "token") String token,
                                                           SearchViaggiDto searchViaggiDto,
                                                           @RequestParam(required = false) boolean prenotabile,
                                                           @RequestParam(required = false) EnumOrdinamento enumOrdinamento,
                                                           @RequestParam(required = false) EnumRatingAutista enumRatingAutista) {
        List<ViaggioResponse> viaggioResponses = viaggioService.getViaggi(searchViaggiDto, enumOrdinamento,prenotabile,enumRatingAutista, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(viaggioResponses);
    }

}
