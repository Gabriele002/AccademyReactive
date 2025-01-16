package it.reactive.torneoDemo.repository.dao.jpaRepository;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoGiocatoreJpa;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoSquadraJpa;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoTifoseriaJpa;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoTorneoJpa;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class DaoPonteSquadraJpa implements DaoSquadra {

    @Autowired
    RepoSquadraJpa repoSquadraJpa;

    @Autowired
    RepoTifoseriaJpa repoTifoseriaJpa;

    @Autowired
    RepoGiocatoreJpa repoGiocatoreJpa;

    @Autowired
    RepoTorneoJpa repoTorneoJpa;

    @Override
    @Transactional
    public SquadraModel create(SquadraDTO squadraDTO) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setNome(Utility.formattaStringaPerDb(squadraDTO.getNome()));
        squadraModel.setColoriSociali(squadraDTO.getColoriSociali());
        squadraModel = repoSquadraJpa.save(squadraModel);
        return squadraModel;
    }

    @Override
    @Transactional
    public void delete(int id) {
        Set<GiocatoriModel> giocatoriModel = repoGiocatoreJpa.findByIdSquadra(id);
        giocatoriModel.forEach(g-> repoGiocatoreJpa.delete(g));
        Optional<TifoseriaModel> tifoseriaModel = repoTifoseriaJpa.findByIdSquadra(id);
        tifoseriaModel.ifPresent(model -> repoTifoseriaJpa.delete(model));
        List<TorneoModel> torneiAssociatiAdUnaSquadra = repoTorneoJpa.findBySquadre_idSquadra(id);
        torneiAssociatiAdUnaSquadra.forEach(t -> t.getSquadre().remove(repoSquadraJpa.findById(id)));
        repoSquadraJpa.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<SquadraModel> findById(int id) throws SQLException {
        return repoSquadraJpa.findById(id);
    }

    @Transactional
    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) {
        return repoSquadraJpa.findAll();
    }

    @Transactional
    @Override
    public Optional<SquadraModel> readForName(String nome) {
        return repoSquadraJpa.findByNome(nome);
    }

    @Override
    @Transactional
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
