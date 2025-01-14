package it.reactive.torneoDemo.repository.dao.implement.entityManagerBase;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.repository.mapper.MapperTorneo;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomRowMapperSquadra;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomRowMapperTorneo;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class TorneoEntityManagerBase implements DaoTorneo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TorneoModel create(TorneoDTO torneoDTO) throws SQLException {
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setNomeTorneo(torneoDTO.getNomeTorneo());
        entityManager.persist(torneoModel);
        return torneoModel;
    }

    @Override
    public Optional<TorneoModel> findById(int id) {
        TorneoModel torneoModel = entityManager.find(TorneoModel.class, id);
        return Optional.of(torneoModel);
    }

    @Override
    public void delete(int id) throws SQLException {
        TorneoModel torneoModel = entityManager.find(TorneoModel.class, id);
        entityManager.remove(torneoModel);
    }

    @Override
    public TorneoModel findByIdWithSquadre(int id) throws SQLException {
        String query = "select t from TorneoModel t join t.squadre where t.id = :id";
        TorneoModel torneoModel = entityManager.createQuery(query, TorneoModel.class)
                .setParameter("id", id)
                .getSingleResult();
        return torneoModel;
    }


    @Override
    public void aggiungoSquadraAlTorneo(int idSquadra, int idTorneo) throws SQLException {
        String query = "insert into squadra_torneo (id_squadra, id_torneo) values (:idSquadra, :idTorneo)";
        entityManager.createNativeQuery(query)
                .setParameter("idSquadra", idSquadra)
                .setParameter("idTorneo", idTorneo)
                .executeUpdate();
    }


    @Override
    public List<TorneoModel> getAllTorneo() throws SQLException {

        List<TorneoModel> torneoModelList = entityManager.createNamedQuery("findAllTornei", TorneoModel.class).getResultList();
        torneoModelList.forEach(torneoModel -> torneoModel.getSquadre()
                .forEach(squadraModel -> {
                    TifoseriaModel tifoseriaModel = squadraModel.getTifoseria();
                    squadraModel.setTifoseria(tifoseriaModel);
                }));
        return torneoModelList;
    }

    @Override
    public List<Integer> readTorniSquadra(int idTorneo) {
        List<Integer> idSquadre = new ArrayList<>();
        TorneoModel torneoModel = entityManager.find(TorneoModel.class, idTorneo);
        Set<SquadraModel> squadraModels = torneoModel.getSquadre();
        squadraModels.forEach(squadraModel -> {
            idSquadre.add(squadraModel.getIdSquadra());
        });

        return idSquadre;
    }

}
