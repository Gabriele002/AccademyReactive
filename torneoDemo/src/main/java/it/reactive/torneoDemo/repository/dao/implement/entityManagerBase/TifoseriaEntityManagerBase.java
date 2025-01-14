package it.reactive.torneoDemo.repository.dao.implement.entityManagerBase;

import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.utility.DaoProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class TifoseriaEntityManagerBase implements DaoTifoseria {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TifoseriaModel create(TifoseriaDTO tifoseriaDTO, int id) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(id);
        tifoseriaModel.setSquadra(squadraModel);
        entityManager.persist(tifoseriaModel);
        return tifoseriaModel;
    }

    @Override
    public Optional<TifoseriaModel> readForIdSquadra(int idSquadra) {
        String query = "select t from tifoseria t where t.squadra.id = :idSquadra";
        try {
            TifoseriaModel tifoseria = entityManager.createQuery(query, TifoseriaModel.class)
                    .setParameter("idSquadra", idSquadra)
                    .getSingleResult();
            return Optional.ofNullable(tifoseria);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public TifoseriaModel update(TifoseriaDTO tifoseriaDTO, int id) throws SQLException {
        TifoseriaModel tifoseriaModel = readForIdSquadra(id).get();
        tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        entityManager.merge(tifoseriaModel);
        return tifoseriaModel;
    }

    @Override
    public void delete(int id) {
        TifoseriaModel tifoseriaModel = entityManager.find(TifoseriaModel.class, id);
        entityManager.remove(tifoseriaModel);
    }
}
