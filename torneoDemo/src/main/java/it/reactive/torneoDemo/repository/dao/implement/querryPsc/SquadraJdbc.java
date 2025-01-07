package it.reactive.torneoDemo.repository.dao.implement.querryPsc;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
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
import java.util.List;
import java.util.Optional;

@Repository
@Profile(DbProfile.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class SquadraJdbc implements DaoSquadra {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DbCostanti dbCostanti;

    @Override
    public SquadraModel create(SquadraDTO squadraDTO) throws SQLException {
        String query = "insert into squadra (nome , colori_sociali) values(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Utility.formattaStringaPerDb(squadraDTO.getNome()));
            ps.setString(2, Utility.formattaStringaPerDb(squadraDTO.getColoriSociali()));
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        SquadraModel squadra = new SquadraModel();
        squadra.setIdSquadra(generatedId);
        squadra.setColoriSociali(squadraDTO.getColoriSociali());
        squadra.setNome(squadraDTO.getNome());

        return squadra;
    }

    @Override
    public void delete(int id) throws SQLException {
        String deleteTifoseriaQuery = "delete from tifoseria where id_squadra = ?";
        jdbcTemplate.update(deleteTifoseriaQuery, id);

        String deleteGiocatoreQuery = "delete from giocatore where id_squadra = ?";
        jdbcTemplate.update(deleteGiocatoreQuery, id);

        String deleteSquadraTorneoQuery = "delete from squadra_torneo where id_squadra = ?";
        jdbcTemplate.update(deleteSquadraTorneoQuery, id);

        String deleteSquadraQuery = "delete from squadra where squadra_id = ?";
        jdbcTemplate.update(deleteSquadraQuery, id);
    }

    @Override
    public Optional<SquadraModel> findById(int id) throws SQLException {
        String query = "select * from squadra where id = ?";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr;
        };

        ResultSetExtractor<Optional<SquadraModel>> rse = rs -> {
            if (rs.next()){
                SquadraModel squadraModel = MapperSquadra.rsToModel(rs);
                return Optional.of(squadraModel);
            }
            return Optional.empty();
        };
        return jdbcTemplate.query(psc, rse);
    }

    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) throws SQLException {
        List<SquadraModel> squadre = new ArrayList<>();
        String querySquadreTifoseria = "select s.*, t.*, t.id as id_tifoseria FROM squadra s LEFT JOIN tifoseria t ON t.id_squadra = s.id";
        jdbcTemplate.query(querySquadreTifoseria, rs -> {
            while (rs.next()) {
                SquadraModel squadraModel = MapperSquadra.rsToModelWithTifoseria(rs);
                if (listaGiocatori) {
                    String queryGiocatori = "select g.nome_cognome, g.id, g.numero_ammonizioni from giocatore g where g.id_squadra = ?";

                    PreparedStatementCreator prs = con -> {
                        PreparedStatement pr = con.prepareStatement(queryGiocatori);
                        pr.setInt(1, squadraModel.getIdSquadra());
                        return pr;
                    };

                    jdbcTemplate.query(prs, rsGiocatori -> {
                        while (rsGiocatori.next()) {
                            GiocatoriModel giocatoriModel = MapperGiocatore.rsToModel(rsGiocatori);
                            squadraModel.getGiocatori().add(giocatoriModel);
                        }
                        return null;
                    });
                }
                squadre.add(squadraModel);
            }
            return null;
        });
        return squadre;
    }

    @Override
    public Optional<SquadraModel> readForName(String nome) {
        String query = "select * from squadra where nome = ?";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, nome);
            return pr;
        };
        ResultSetExtractor<Optional<SquadraModel>> rse = rs -> {
            if (rs.next()){
                SquadraModel squadraModel =MapperSquadra.rsToModel(rs);
                return Optional.of(squadraModel);
            }
            return Optional.empty();
        };
        return jdbcTemplate.query(psc, rse);
    }
}
