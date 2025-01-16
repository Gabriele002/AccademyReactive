package it.reactive.torneoDemo.repository.dao.jpaRepository;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoGiocatoreJpa;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoSquadraJpa;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class DaoPonteGiocatoreJpa implements DaoGiocatori {

    @Autowired
    RepoGiocatoreJpa repoGiocatoreJpa;

    @Autowired
    RepoSquadraJpa repoSquadraJpa;

    @Override
    @Transactional
    public GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException {
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        giocatoriModel.setNomeCognome(Utility.formattaStringaPerDb(giocatoreDTO.getNomeCognome()));
        giocatoriModel.setNumeroAmmonizioni(0);
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(id);
        giocatoriModel.setSquadra(squadraModel);
        return repoGiocatoreJpa.save(giocatoriModel);
    }

    @Override
    @Transactional
    public Set<GiocatoriModel> readGiocatoriWithIdSquadra(int idSquadra) {
        Optional<SquadraModel> squadraOptional = repoSquadraJpa.findById(idSquadra);
        Set<GiocatoriModel> giocatoriModelSet = new HashSet<>();
        if (squadraOptional.isPresent()) {
            SquadraModel squadraModel = squadraOptional.get();
            giocatoriModelSet = squadraModel.getGiocatori();
        }

        return giocatoriModelSet;
    }

    @Override
    @Transactional
    public Optional<GiocatoriModel> readForName(String nome) {
        return repoGiocatoreJpa.findByNomeCognome(nome);
    }

    @Override
    @Transactional
    public Optional<GiocatoriModel> readForId(int id) {
        return repoGiocatoreJpa.findById(id);
    }

    @Override
    @Transactional
    public void incrementaAmmonizioni(int id) throws SQLException {
        GiocatoriModel giocatoriModel = repoGiocatoreJpa.findById(id).get();
        giocatoriModel.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni() + 1);
        repoGiocatoreJpa.save(giocatoriModel);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repoGiocatoreJpa.deleteById(id);
    }

}
