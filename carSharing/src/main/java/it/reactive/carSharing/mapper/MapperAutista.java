package it.reactive.carSharing.mapper;

import it.reactive.carSharing.dto.UtenteRegisterDto;
import it.reactive.carSharing.model.Autisti;
import it.reactive.carSharing.response.AutistaResponse;
import it.reactive.carSharing.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperAutista {

    @Autowired
    Utility utility;

    public Autisti dtoToModel(UtenteRegisterDto utenteRegisterDto){
        Autisti autista = new Autisti();
        autista.setTarga(utenteRegisterDto.getTarga());
        autista.setNome(utenteRegisterDto.getNome());
        autista.setCognome(utenteRegisterDto.getCognome());
        autista.setPsw(utility.codificaInBase64(utenteRegisterDto.getPassword()));
        autista.setEMail(utenteRegisterDto.getEmail());
        return autista;
    }

    public AutistaResponse modelToResponse(Autisti autista){
        AutistaResponse autistaResponse = new AutistaResponse();
        autistaResponse.setCognome(autista.getCognome());
        autistaResponse.setNome(autista.getNome());
        autistaResponse.setCognome(autista.getCognome());
        autistaResponse.setEMail(autista.getEMail());
        autistaResponse.setTarga(autista.getTarga());
        return autistaResponse;
    }
}
