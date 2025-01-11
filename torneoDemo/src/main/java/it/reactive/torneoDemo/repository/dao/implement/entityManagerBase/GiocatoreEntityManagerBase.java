package it.reactive.torneoDemo.repository.dao.implement.entityManagerBase;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.utility.DaoProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class GiocatoreEntityManagerBase implements DaoGiocatori {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException {
        return null;
    }

    @Override
    public Set<GiocatoriModel> readGiocatoriWithIdSquadra(int id) throws Exception {
        return Collections.emptySet();
    }

    @Override
    public Optional<GiocatoriModel> readForName(String nome) {
        return Optional.empty();
    }

    @Override
    public Optional<GiocatoriModel> readForId(int id) {
        GiocatoriModel giocatoriModel = entityManager.find(GiocatoriModel.class, id);
        return Optional.of(giocatoriModel);
    }

    @Override
    @Transactional
    public void incrementaAmmonizioni(int id) throws SQLException {
        GiocatoriModel giocatoriModel = entityManager.find(GiocatoriModel.class, id);
        giocatoriModel.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni() + 1);
        entityManager.merge(giocatoriModel);
    }

    @Override
    public void delete(int id) {

    }
}

