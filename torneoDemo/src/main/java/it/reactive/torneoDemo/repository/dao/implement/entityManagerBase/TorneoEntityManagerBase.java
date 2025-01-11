package it.reactive.torneoDemo.repository.dao.implement.entityManagerBase;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.SquadraModel;
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
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class TorneoEntityManagerBase implements DaoTorneo {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public TorneoModel create(TorneoDTO torneoDTO) throws SQLException {
        return null;
    }

    @Override
    public Optional<TorneoModel> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public TorneoModel findByIdWithSquadre(int id) throws SQLException {
        return null;
    }

    @Override
    public void aggiungoSquadraAlTorneo(int idSquadra, int idTorneo) throws SQLException {

    }

    @Override
    public List<TorneoModel> getAllTorneo() throws SQLException {
        return Collections.emptyList();
    }

    @Override
    public List<Integer> readTorniSquadra(int idTorneo) {
        return Collections.emptyList();
    }
}
