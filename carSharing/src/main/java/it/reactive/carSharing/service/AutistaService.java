package it.reactive.carSharing.service;

import it.reactive.carSharing.dto.CreaViaggioDto;
import it.reactive.carSharing.dto.enumDto.EnumTipoUtente;
import it.reactive.carSharing.exception.CodiceErrori;
import it.reactive.carSharing.exception.CustomException;
import it.reactive.carSharing.mapper.ViaggioMapper;
import it.reactive.carSharing.model.Viaggi;
import it.reactive.carSharing.repository.AutistaRepo;
import it.reactive.carSharing.repository.ViaggiiRepo;
import it.reactive.carSharing.response.Token;
import it.reactive.carSharing.response.ViaggioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AutistaService {

    @Autowired
    ViaggiiRepo viaggiiRepo;

    @Autowired
    AutistaRepo autistaRepo;

    @Autowired
    TokenService tokenService;

    @Autowired
    ViaggioMapper viaggioMapper;

    public ViaggioResponse creaViaggioDto(CreaViaggioDto creaViaggioDto, String token){
        Viaggi viaggioModel = viaggioMapper.dtoToEntity(creaViaggioDto);
        Token tokenValid = tokenService.isTokenValid(token);
        viaggioModel.setAutista(autistaRepo.findByeMail(tokenValid.getEmail()).get());
        if (creaViaggioDto.getDataPartenza().isBefore(creaViaggioDto.getDataArrivo()) && creaViaggioDto.getDataPartenza().isBefore(LocalDateTime.now())) {
            throw new CustomException(CodiceErrori.DATANONVALIDA);
        }
        if (tokenValid.getTipoUtente().equals(EnumTipoUtente.AUTISTA)){
            viaggiiRepo.save(viaggioModel);
        } else {
            throw new CustomException(CodiceErrori.ERRORE_UTENTENONVALIDO);
        }
        return viaggioMapper.entityToResponse(viaggioModel);
    }


}
