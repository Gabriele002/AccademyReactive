package it.reactive.torneoDemo.repository.dao.implement.jdbcQueryForX;

import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.mapper.MapperTifoseria;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class TifoseriaJdbcQuerryForX implements DaoTifoseria {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public TifoseriaModel create(TifoseriaDTO tifoseriaDTO, int id) throws SQLException {
        String createTifoseria = "insert into tifoseria (nome_tifoseria,id_squadra) values (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(createTifoseria, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Utility.formattaStringaPerDb(tifoseriaDTO.getNomeTifoseria()));
            return ps;
        }, keyHolder);

        Integer generatedId = (Integer) keyHolder.getKeys().get("id");
        TifoseriaModel tifoseria = new TifoseriaModel();
        tifoseria.setIdTifoseria(generatedId);
        tifoseria.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        return tifoseria;
    }

    @Override
    public Optional<TifoseriaModel> readForIdSquadra(int idSquadra) throws SQLException {
        String query = "select t.* from tifoseria t where id_squadra = ?";
        try {
            Map<String, Object> squadraMap = jdbcTemplate.queryForMap(query, new Object[]{idSquadra});
            TifoseriaModel tifoseriaModel = new TifoseriaModel();
            tifoseriaModel.setIdTifoseria(((Integer) squadraMap.get("id")));
            tifoseriaModel.setNomeTifoseria(((String) squadraMap.get("nome_tifoseria")));
            return Optional.of(tifoseriaModel);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public TifoseriaModel update(TifoseriaDTO tifoseriaDTO, int idSquadra) throws SQLException {
        String queryUpdate = "update tifoseria set nome_tifoseria = ? where id_squadra = ?";
        jdbcTemplate.update(queryUpdate, tifoseriaDTO.getNomeTifoseria(), idSquadra);


        String querySelect = "select * from tifoseria where id_squadra = ?";
        PreparedStatementCreator pscSelect = connection -> {
            PreparedStatement ps = connection.prepareStatement(querySelect);
            ps.setInt(1, idSquadra);
            return ps;
        };
        ResultSetExtractor<TifoseriaModel> rse = rs -> {
            if (rs.next()) {
                return MapperTifoseria.rsToModel(rs);
            }
            return null;
        };
        return jdbcTemplate.query(pscSelect, rse);

    }
}
