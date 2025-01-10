package it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry.rowMapper.CustomRowMapperGiocatore;
import it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry.rowMapper.CustomRowMapperSquadra;
import it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry.rowMapper.CustomRowMapperTorneo;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperTorneo;
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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JDBC_QUERY)
public class TorneoJdbcQuerry implements DaoTorneo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public TorneoModel create(TorneoDTO torneoDTO) throws SQLException {
        String queryTorneo = "insert into torneo (nome_torneo) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(queryTorneo, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Utility.formattaStringaPerDb(torneoDTO.getNomeTorneo()));
            return ps;
        }, keyHolder);

        Integer generateId = (Integer) keyHolder.getKeys().get("id");
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setIdTorneo(generateId);
        torneoModel.setNomeTorneo(torneoDTO.getNomeTorneo());
        return torneoModel;
    }

    @Override
    public Optional<TorneoModel> findById(int id) {
        String query = "select * from torneo where id = :idTorneo";
        MapSqlParameterSource par = new MapSqlParameterSource();
        par.addValue("idTorneo", id);
        List<TorneoModel> torneoModelList = namedParameterJdbcTemplate.query(query, par, new CustomRowMapperTorneo() );
        if (torneoModelList.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(torneoModelList.get(0));
    }

    @Override
    @Transactional
    public void delete(int id) throws SQLException {
        String querryDeleteSquadraTorneo = "delete from squadra_torneo where id_torneo = ?";
        jdbcTemplate.update(querryDeleteSquadraTorneo, id);
        String querryDelete = "delete from torneo where id= ?";
        jdbcTemplate.update(querryDelete, id);
    }

    @Override
    public TorneoModel findByIdWithSquadre(int id) throws SQLException {
        String querryFind = "select t.*, s.id as squadra_id, s.nome as nome_squadra, s.colori_sociali " +
                "from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "where t.id = ?";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pr = con.prepareStatement(querryFind);
            pr.setInt(1, id);
            return pr;
        };

        ResultSetExtractor<TorneoModel> rse = rs -> {
            TorneoModel torneoModel = null;
            if (rs.next()) {
                torneoModel = MapperTorneo.rsToModelWithSquadra(rs);
            }
            return torneoModel;
        };
        return jdbcTemplate.query(psc, rse);
    }

    @Override
    public void aggiungoSquadraAlTorneo(int idSquadra, int idTorneo) throws SQLException {
        String querryInsert = "insert into squadra_torneo (id_squadra , id_torneo) values (?, ?)";
        jdbcTemplate.update(querryInsert, idSquadra, idTorneo);
    }

    @Override
    public List<TorneoModel> getAllTorneo() throws SQLException {
        String querryFind = "select t.*, s.id as id_squadra, s.nome, s.colori_sociali, tf.nome_tifoseria, tf.id as id_tifoseria " +
                "from torneo t " +
                "left join squadra_torneo st on t.id = st.id_torneo " +
                "left join squadra s on st.id_squadra = s.id " +
                "left join tifoseria tf on tf.id_squadra = s.id";

        return namedParameterJdbcTemplate.query(querryFind, new CustomRowMapperTorneo());
    }

    @Override
    public List<Integer> readTorniSquadra(int idTorneo) {
        String querry = "SELECT s.id " +
                "FROM squadra s " +
                "JOIN squadra_torneo st ON s.id = st.id_squadra " +
                "JOIN torneo t ON t.id = st.id_torneo " +
                "WHERE t.id = :idTorneo";

        MapSqlParameterSource par = new MapSqlParameterSource();
        par.addValue("idTorneo", idTorneo);
        List<SquadraModel> squadraModels = namedParameterJdbcTemplate.query(querry, par, new CustomRowMapperSquadra());
        List<Integer> idSquadre = new ArrayList<>();
        squadraModels.forEach(s -> idSquadre.add(s.getIdSquadra()));
        return idSquadre;
    }
}
