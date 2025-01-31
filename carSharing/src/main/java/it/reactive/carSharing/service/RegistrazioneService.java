package it.reactive.carSharing.service;

import it.reactive.carSharing.dto.enumDto.EnumTipoUtente;
import it.reactive.carSharing.dto.UtenteRegisterDto;
import it.reactive.carSharing.exception.CodiceErrori;
import it.reactive.carSharing.exception.CustomException;
import it.reactive.carSharing.mapper.MapperAutista;
import it.reactive.carSharing.mapper.MapperPassegero;
import it.reactive.carSharing.model.Autisti;
import it.reactive.carSharing.model.Passeggeri;
import it.reactive.carSharing.repository.AutistaRepo;
import it.reactive.carSharing.repository.PasseggeriRepo;
import it.reactive.carSharing.utility.Utility;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrazioneService {


    AutistaRepo autistaRepo;
    PasseggeriRepo passeggeriRepo;
    MapperAutista mapperAutista;
    MapperPassegero mapperPasseggeri;
    Utility utility;

    public RegistrazioneService(AutistaRepo autistaRepo, PasseggeriRepo passeggeriRepo, MapperAutista mapperAutista, MapperPassegero mapperPasseggeri, Utility utility) {
        this.autistaRepo = autistaRepo;
        this.passeggeriRepo = passeggeriRepo;
        this.mapperAutista = mapperAutista;
        this.mapperPasseggeri = mapperPasseggeri;
        this.utility = utility;
    }

    public void registrazione(UtenteRegisterDto utenteRegisterDto, EnumTipoUtente tipoUtente) {

        Optional<Passeggeri> existingPassegero = passeggeriRepo.findByeMail(utenteRegisterDto.getEmail());
        if (existingPassegero.isPresent()) {
            throw new CustomException(CodiceErrori.ERRORE_EMAILGIAVALIDA);
        }

        Optional<Autisti> existingAutista = autistaRepo.findByeMail(utenteRegisterDto.getEmail());
        if (existingAutista.isPresent()) {
            throw new CustomException(CodiceErrori.ERRORE_EMAILGIAVALIDA);
        }

        boolean pswValida = utility.isValidPassword(utenteRegisterDto.getPassword());
        if (!pswValida) {
            throw new CustomException(CodiceErrori.ERRORE_PSWNONVALIDA);
        }

        if (tipoUtente.equals(EnumTipoUtente.AUTISTA)) {
            Autisti autista = mapperAutista.dtoToModel(utenteRegisterDto);
            autistaRepo.save(autista);
        } else {
            Passeggeri passeggero = mapperPasseggeri.dtoToModel(utenteRegisterDto);
            passeggeriRepo.save(passeggero);
        }
    }
}

