package it.reactive.torneoDemo.repository.dao.implement.querryPsc;

import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.mapper.MapperTifoseria;
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
import java.util.Optional;

@Repository
@Profile(DbProfile.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class TifoseriaJdbc implements DaoTifoseria {

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

        int generatedId = keyHolder.getKey().intValue();
        TifoseriaModel tifoseria = new TifoseriaModel();
        tifoseria.setIdTifoseria(generatedId);
        tifoseria.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        return tifoseria;
    }

    @Override
    public Optional<TifoseriaModel> readForIdSquadra(int idSquadra) throws SQLException {
        String query = "select t.* from tifoseria t where id_squadra = ?";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, idSquadra);
            return pr;
        };

        ResultSetExtractor<Optional<TifoseriaModel>> rse = rs -> {
            if (rs.next()) {
                TifoseriaModel tifoseriaModel = MapperTifoseria.rsToModel(rs);
                return Optional.of(tifoseriaModel);
            }
            return Optional.empty();
        };
        return jdbcTemplate.query(psc, rse);
    }

    @Override
    public TifoseriaModel update(TifoseriaDTO tifoseriaDTO, int idSquadra) throws SQLException {
        String queryUpdate = "UPDATE tifoseria SET nome_tifoseria = ? WHERE id_squadra = ?";
        PreparedStatementCreator pscUpdate = connection -> {
            PreparedStatement ps = connection.prepareStatement(queryUpdate);
            ps.setString(1, tifoseriaDTO.getNomeTifoseria());
            ps.setInt(2, idSquadra);
            return ps;
        };

        String querySelect = "SELECT * FROM tifoseria WHERE id_squadra = ?";

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
        jdbcTemplate.update(pscUpdate);

        return jdbcTemplate.query(pscSelect, rse);

    }

}
