package it.reactive.torneoDemo.repository.dao.jpaRepository;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoGiocatoreJpa;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class DaoPonteGiocatoreJpa implements DaoGiocatori {

    @Autowired
    RepoGiocatoreJpa repoGiocatoreJpa;

    @Override
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
    public Set<GiocatoriModel> readGiocatoriWithIdSquadra(int id){
        Set<GiocatoriModel> giocatoriModelSet = new HashSet<>();
        Optional<GiocatoriModel> giocatoriModel = repoGiocatoreJpa.findById(id);
        if (giocatoriModel.isPresent()){
            SquadraModel squadraModel = giocatoriModel.get().getSquadra();
            giocatoriModel.get().setSquadra(squadraModel);
            giocatoriModelSet.add(giocatoriModel.get());
        }
        return giocatoriModelSet;
    }

    @Override
    public Optional<GiocatoriModel> readForName(String nome) {
        return repoGiocatoreJpa.findByNomeCognome(nome);
    }

    @Override
    public Optional<GiocatoriModel> readForId(int id) {
        return repoGiocatoreJpa.findById(id);
    }

    @Override
    public void incrementaAmmonizioni(int id) throws SQLException {
        GiocatoriModel giocatoriModel = repoGiocatoreJpa.findById(id).get();
        giocatoriModel.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni() + 1);
        repoGiocatoreJpa.save(giocatoriModel);
    }

    @Override
    public void delete(int id) {
        repoGiocatoreJpa.deleteById(id);
    }

}
