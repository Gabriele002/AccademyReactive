package it.reactive.torneoDemo.repository.dao.implement.entityManagerBase;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class GiocatoreEntityManagerBase implements DaoGiocatori {

    public static final String NOME_COGNOME_QUERRY_NAME = "nomeCognome";
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException {
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        giocatoriModel.setNomeCognome(Utility.formattaStringaPerDb(giocatoreDTO.getNomeCognome()));
        giocatoriModel.setNumeroAmmonizioni(0);
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(id);
        giocatoriModel.setSquadra(squadraModel);
        entityManager.persist(giocatoriModel);
        return giocatoriModel;
    }

    @Override
    public Set<GiocatoriModel> readGiocatoriWithIdSquadra(int id) throws Exception {
        Set<GiocatoriModel> giocatoriModelSet = new HashSet<>();
        SquadraModel squadraModel = entityManager.find(SquadraModel.class, id);
        if (squadraModel != null) {
            giocatoriModelSet.addAll(squadraModel.getGiocatori());
        }
        return giocatoriModelSet;
    }

    @Override
    public Optional<GiocatoriModel> readForName(String nome) {
        TypedQuery<GiocatoriModel> query = entityManager.createNamedQuery("findByNomeCognome", GiocatoriModel.class);
        query.setParameter(NOME_COGNOME_QUERRY_NAME, nome);
        try {
            GiocatoriModel giocatore = query.getSingleResult();
            return Optional.of(giocatore);
        } catch (NoResultException e) {
            return Optional.empty();
        }
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
        GiocatoriModel giocatore = entityManager.find(GiocatoriModel.class, id);
        entityManager.remove(giocatore);
    }
}
