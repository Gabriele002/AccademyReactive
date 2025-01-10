package it.reactive.torneoDemo.repository.dao.jpaRepository;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoSquadraJpa;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoTorneoJpa;
import it.reactive.torneoDemo.utility.DaoProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class DaoPonteTorneoJpa implements DaoTorneo {

    @Autowired
    RepoTorneoJpa repoTorneoJpa;

    @Autowired
    RepoSquadraJpa repoSquadraJpa;


    @Override
    public TorneoModel create(TorneoDTO torneoDTO) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(torneoDTO.getNomeTorneo());
        return repoTorneoJpa.save(torneoModel);
    }

    @Override
    public Optional<TorneoModel> findById(int id) {
        return repoTorneoJpa.findById(id);
    }

    @Override
    public void delete(int id) throws SQLException {
        TorneoModel torneoModel = repoTorneoJpa.findById(id).get();
        torneoModel.getSquadre().clear();
        repoTorneoJpa.save(torneoModel);
        repoTorneoJpa.delete(torneoModel);
    }

    @Override
    public TorneoModel findByIdWithSquadre(int id) throws SQLException {
        TorneoModel torneoModel = repoTorneoJpa.findById(id).get();
        Set<SquadraModel> squadraModels = torneoModel.getSquadre();
        torneoModel.setSquadre(squadraModels);
        return torneoModel;
    }

    @Override
    public void aggiungoSquadraAlTorneo(int idSquadra, int idTorneo) throws SQLException {
        TorneoModel torneo = repoTorneoJpa.findById(idTorneo).get();
        SquadraModel squadra = repoSquadraJpa.findById(idSquadra).get();
        torneo.getSquadre().add(squadra);
        squadra.getTornei().add(torneo);
        repoTorneoJpa.save(torneo);
    }

    @Override
    public List<TorneoModel> getAllTorneo(){
        List<TorneoModel>torneoModelList = repoTorneoJpa.findAll();
        torneoModelList.forEach(torneoModel ->torneoModel.getSquadre()
                .forEach(squadraModel -> {
                    TifoseriaModel tifoseriaModel = squadraModel.getTifoseria();
                    squadraModel.setTifoseria(tifoseriaModel);
                }));
        return torneoModelList;
    }

    @Override
    public List<Integer> readTorniSquadra(int idTorneo) {
        List<Integer> idSquadre = new ArrayList<>();
        TorneoModel torneoModel = repoTorneoJpa.findById(idTorneo).get();
        Set<SquadraModel> squadraModels = torneoModel.getSquadre();
        squadraModels.forEach(squadraModel -> {
            idSquadre.add(squadraModel.getIdSquadra());
        });
        return idSquadre;
    }
}
