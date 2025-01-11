package it.reactive.torneoDemo.repository.dao.implement.entityManagerBase;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomRowMapperGiocatore;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomRowMapperSquadra;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomerMapRowSquadraWithTifoseria;
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
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
public class SquadraEntityManagerBase implements DaoSquadra {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public SquadraModel create(SquadraDTO torneoDTO) throws SQLException {
        return null;
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public Optional<SquadraModel> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) throws SQLException {
        return Collections.emptyList();
    }

    @Override
    public Optional<SquadraModel> readForName(String nome) {
        return Optional.empty();
    }

    @Override
    public List<Integer> recuperoTornei(int idSquadra) throws SQLException {
        return Collections.emptyList();
    }
}
