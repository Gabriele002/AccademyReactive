package it.reactive.carSharing.mapper;

import it.reactive.carSharing.dto.UtenteRegisterDto;
import it.reactive.carSharing.model.Passeggeri;
import it.reactive.carSharing.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperPassegero {

    @Autowired
    Utility utility;

    public Passeggeri dtoToModel(UtenteRegisterDto utenteRegisterDto){
        Passeggeri passegero = new Passeggeri();
        passegero.setNome(utenteRegisterDto.getNome());
        passegero.setCognome(utenteRegisterDto.getCognome());
        passegero.setPsw(utility.codificaInBase64(utenteRegisterDto.getPassword()));
        passegero.setEMail(utenteRegisterDto.getEmail());
        return passegero;
    }
}
