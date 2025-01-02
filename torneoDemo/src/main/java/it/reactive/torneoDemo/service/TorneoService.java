package it.reactive.torneoDemo.service;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.dto.resource.TorneoResponse;
import it.reactive.torneoDemo.exception.CodiceErrori;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.exception.TorneoNonTrovatoException;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.repository.mapper.MapperTorneo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class TorneoService {

    @Autowired
    DaoTorneo daoTorneo;

    @Autowired
    DaoSquadra daoSquadra;


    public TorneoResponse createTorneo (TorneoDTO torneoDTO) throws SQLException {
        TorneoModel torneoModel = daoTorneo.create(torneoDTO);
        return MapperTorneo.modelToResponde(torneoModel);
    }


    public TorneoResponse aggiungoSquadra(int idSquadra, int idTorneo) throws SQLException {
        SquadraModel squadraModel = daoSquadra.findById(idSquadra)
                .orElseThrow(() -> new SquadraNonPresenteException(CodiceErrori.ERRORE_SQUADRANONPRESENTE));

        TorneoModel torneoModel = daoTorneo.findById(idTorneo)
                .orElseThrow(() -> new TorneoNonTrovatoException(CodiceErrori.ERRORE_TORNERONONTROVATO));



        daoTorneo.aggiungoSquadraAlTorneo(idSquadra, idTorneo);

        return MapperTorneo.modelToResponde(torneoModel);
    }
}
