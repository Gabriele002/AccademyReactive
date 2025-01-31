package it.reactive.carSharing.service;

import it.reactive.carSharing.dto.LogginDto;
import it.reactive.carSharing.dto.enumDto.EnumTipoUtente;
import it.reactive.carSharing.exception.CodiceErrori;
import it.reactive.carSharing.exception.CustomException;
import it.reactive.carSharing.model.Utenti;
import it.reactive.carSharing.repository.AutistaRepo;
import it.reactive.carSharing.repository.PasseggeriRepo;
import it.reactive.carSharing.response.Token;
import it.reactive.carSharing.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AutistaRepo autistaRepo;

    @Autowired
    PasseggeriRepo passeggeriRepo;

    @Autowired
    Utility utility;

    @Autowired
    TokenService tokenService;


    public String login(LogginDto loginDto) {
        Utenti utente = null;
        EnumTipoUtente tipoUtente = null;

        if (autistaRepo.findByeMail(loginDto.getEmail()).isPresent()) {
            utente = autistaRepo.findByeMail(loginDto.getEmail()).get();
            tipoUtente = EnumTipoUtente.AUTISTA;
        } else if (passeggeriRepo.findByeMail(loginDto.getEmail()).isPresent()) {
            utente = passeggeriRepo.findByeMail(loginDto.getEmail()).get();
            tipoUtente = EnumTipoUtente.PASSEGGERO;
        } else {
            throw new CustomException(CodiceErrori.ERRORE_UTENTENONTROVATO);
        }
        if (loginDto.getPsw().equals(utente.getPsw())){
            throw new CustomException(CodiceErrori.ERRORE_PSWNONVALIDA);
        }
        Token token = new Token(utente.getEMail(), tipoUtente);
        return tokenService.getToken(token);
    }







}

