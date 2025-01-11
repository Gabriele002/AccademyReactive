package it.reactive.torneoDemo.repository.dao.jpaRepository;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoSquadraJpa;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class DaoPonteSquadraJpa implements DaoSquadra {

    @Autowired
    RepoSquadraJpa repoSquadraJpa;

    @Override
    public SquadraModel create(SquadraDTO squadraDTO) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setNome(Utility.formattaStringaPerDb(squadraDTO.getNome()));
        squadraModel.setColoriSociali(squadraDTO.getColoriSociali());
        squadraModel = repoSquadraJpa.save(squadraModel);
        return squadraModel;
    }

    @Override
    public void delete(int id) {
        repoSquadraJpa.deleteById(id);
    }

    @Override
    public Optional<SquadraModel> findById(int id) throws SQLException {
        return repoSquadraJpa.findById(id);
    }

    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) {
        return repoSquadraJpa.findAll();
    }

    @Override
    public Optional<SquadraModel> readForName(String nome) {
        return repoSquadraJpa.findByNome(nome);
    }

    @Override
    public List<Integer> recuperoTornei(int idSquadra) {
        SquadraModel squadraModel = repoSquadraJpa.findById(idSquadra).get();
        Set<TorneoModel> torneoModelList = squadraModel.getTornei();
        List<Integer> idTorneiAssociati = new ArrayList<>();
        torneoModelList.forEach(torneoModel -> {
            idTorneiAssociati.add(torneoModel.getIdTorneo());
        });
        return idTorneiAssociati;
    }
}
