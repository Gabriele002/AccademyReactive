package it.reactive.torneoDemo.repository.dao.implement.querryPsc;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.repository.mapper.MapperTorneo;
import it.reactive.torneoDemo.utility.DbCostanti;
import it.reactive.torneoDemo.utility.DbProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile(DbProfile.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class TorneoJdbc implements DaoTorneo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DbCostanti db;

    @Override
    public TorneoModel create(TorneoDTO torneoDTO) throws SQLException {
        String queryTorneo = "insert into " + db.TORNEO_TABLE + " (" + db.TORNEO_NOME_TORNEO_COL + ") " + "values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(queryTorneo, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Utility.formattaStringaPerDb(torneoDTO.getNomeTorneo()));
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        TorneoModel torneoModel = new TorneoModel();
        torneoModel.setIdTorneo(generatedId);
        torneoModel.setNomeTorneo(torneoModel.getNomeTorneo());
        return torneoModel;
    }

    @Override
    public Optional<TorneoModel> findById(int id) {
        String query = "select * from torneo where id = ?";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr;
        };
        ResultSetExtractor<Optional<TorneoModel>> rse = rs -> {
            if (rs.next()){
                TorneoModel torneoModel = MapperTorneo.rsToModel(rs);
                return Optional.of(torneoModel);
            }
            return Optional.empty();
        };
        return jdbcTemplate.query(psc, rse);
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public TorneoModel findByIdWithSquadra(int id) throws SQLException {
        String querryFind = "select t.*, s.id as squadra_id, s.nome as nome_squadra, s.colori_sociali from torneo t join squadra_torneo st on t.id = st.id_torneo join squadra s on st.id_squadra = s.id where t.id = ?";
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

    }

    @Override
    public List<TorneoModel> getAllTorneo() throws SQLException {
        return Collections.emptyList();
    }

    @Override
    public List<Integer> readTorniSquadra(int idTorneo) {
        String querry = "SELECT s.id " +
                "FROM squadra s " +
                "JOIN squadra_torneo st ON s.id = st.id_squadra " +
                "JOIN torneo t ON t.id = st.id_torneo " +
                "WHERE t.id = ?";

        PreparedStatementCreator prc = con -> {
            PreparedStatement pr = con.prepareStatement(querry);
            pr.setInt(1, idTorneo);
            return pr;
        };

        ResultSetExtractor<List<Integer>> rse = rs -> {
            List<Integer> squadreList = new ArrayList<>();
            while (rs.next()) {
                squadreList.add(rs.getInt("id"));
            }
            return squadreList;
        };
        return jdbcTemplate.query(prc, rse);
    }
}
