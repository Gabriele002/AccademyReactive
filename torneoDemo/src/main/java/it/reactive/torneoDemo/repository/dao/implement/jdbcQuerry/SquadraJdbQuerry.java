package it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomRowMapperGiocatore;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomRowMapperSquadra;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomerMapRowSquadraWithTifoseria;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
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
public class SquadraJdbQuerry implements DaoSquadra {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    TifoseriaJdbcQuerry tifoseriaJdbcQuerry;

    @Autowired
    GiocatoreJdbcQuerry giocatoreJdbcQuerry;


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

        int generateId = (int) keyHolder.getKeys().get("id");
        SquadraModel squadra = new SquadraModel();
        squadra.setIdSquadra(generateId);
        squadra.setColoriSociali(squadraDTO.getColoriSociali());
        squadra.setNome(squadraDTO.getNome());

        return squadra;
    }

    @Override
    @Transactional
    public void delete(int id) throws SQLException {
        tifoseriaJdbcQuerry.delete(id);
        giocatoreJdbcQuerry.delete(id);

        String deleteSquadraTorneoQuery = "delete from squadra_torneo where id_squadra = ?";
        jdbcTemplate.update(deleteSquadraTorneoQuery, id);

        String deleteSquadraQuery = "delete from squadra where id = ?";
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
            if (rs.next()) {
                SquadraModel squadraModel = MapperSquadra.rsToModel(rs);
                return Optional.of(squadraModel);
            }
            return Optional.empty();
        };
        return jdbcTemplate.query(psc, rse);
    }

    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) throws SQLException {
        String querySquadreTifoseria = "select s.*, t.*, t.id as id_tifoseria FROM squadra s LEFT JOIN tifoseria t ON t.id_squadra = s.id";
        List<SquadraModel> squadraModelList = jdbcTemplate.query(querySquadreTifoseria, new CustomerMapRowSquadraWithTifoseria());
        squadraModelList.forEach(s -> {
                    if (listaGiocatori) {
                        String queryGiocatori = "select g.nome_cognome, g.id, g.numero_ammonizioni from giocatore g where g.id_squadra = :idSquadra";
                        MapSqlParameterSource par = new MapSqlParameterSource();
                        par.addValue("idSquadra", s.getIdSquadra());
                        List<GiocatoriModel> giocatoriModelsList = namedParameterJdbcTemplate.query(queryGiocatori,par,new CustomRowMapperGiocatore());
                        //List<GiocatoriModel> giocatoriModels = jdbcTemplate.query(queryGiocatori, new CustomRowMapperGiocatore(), namedParameterJdbcTemplate.s.getIdSquadra());
                        Set<GiocatoriModel> giocatoriModelsSet = new HashSet<>(giocatoriModelsList);
                        s.setGiocatori(giocatoriModelsSet);
                    }

                }
        );
        return squadraModelList;
    }

    @Override
    public Optional<SquadraModel> readForName(String nome) {
        String query = "select * from squadra where nome = :nomeSquadra";
        MapSqlParameterSource par = new MapSqlParameterSource();
        par.addValue("nomeSquadra", nome);
        List<SquadraModel> squadraModelList = namedParameterJdbcTemplate.query(query, par, new CustomRowMapperSquadra());
        if (squadraModelList.isEmpty()) {
            return Optional.empty();
        }
        SquadraModel squadraModel = squadraModelList.get(0);
        return Optional.of(squadraModel);
    }
}
