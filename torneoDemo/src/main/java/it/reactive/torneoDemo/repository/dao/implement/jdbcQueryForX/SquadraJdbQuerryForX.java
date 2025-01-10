package it.reactive.torneoDemo.repository.dao.implement.jdbcQueryForX;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry.rowMapper.CustomRowMapperGiocatore;
import it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry.rowMapper.CustomRowMapperSquadra;
import it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry.rowMapper.CustomerMapRowSquadraWithTifoseria;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
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
@Profile(DaoProfile.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class SquadraJdbQuerryForX implements DaoSquadra {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


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
        String deleteTifoseriaQuery = "delete from tifoseria where id_squadra = ?";
        jdbcTemplate.update(deleteTifoseriaQuery, id);

        String deleteGiocatoreQuery = "delete from giocatore where id_squadra = ?";
        jdbcTemplate.update(deleteGiocatoreQuery, id);

        String deleteSquadraTorneoQuery = "delete from squadra_torneo where id_squadra = ?";
        jdbcTemplate.update(deleteSquadraTorneoQuery, id);

        String deleteSquadraQuery = "delete from squadra where id = ?";
        jdbcTemplate.update(deleteSquadraQuery, id);
    }

    @Override
    public Optional<SquadraModel> findById(int id) throws SQLException {
        String query = "select * from squadra where id = ?";
        try {
            Map<String, Object> squadraMap = jdbcTemplate.queryForMap(query, new Object[]{id});
            SquadraModel squadraModel = new SquadraModel();
            squadraModel.setIdSquadra(((Integer) squadraMap.get("id")));
            squadraModel.setNome(((String) squadraMap.get("nome")));
            squadraModel.setColoriSociali((String) squadraMap.get("colori_sociali"));
            return Optional.of(squadraModel);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) throws SQLException {
        String querySquadreTifoseria = "select s.*, t.*, t.id as id_tifoseria FROM squadra s LEFT JOIN tifoseria t ON t.id_squadra = s.id";
        List<Map<String, Object>> mapSquadra = jdbcTemplate.queryForList(querySquadreTifoseria);
        List<SquadraModel> squadraModels = new ArrayList<>();
        mapSquadra.forEach(stringObjectMap -> {
            SquadraModel squadraModel = new SquadraModel();
            squadraModel.setIdSquadra((Integer) stringObjectMap.get("id"));
            squadraModel.setNome((String) stringObjectMap.get("nome"));
            squadraModel.setColoriSociali((String) stringObjectMap.get("colori_sociali"));
            TifoseriaModel tifoseriaModel = new TifoseriaModel();
            tifoseriaModel.setIdTifoseria((Integer) stringObjectMap.get("id_tifoseria"));
            tifoseriaModel.setNomeTifoseria((String) stringObjectMap.get("nome_tifoseria"));
            squadraModel.setTifoseria(tifoseriaModel);
            squadraModels.add(squadraModel);
        });
        squadraModels.forEach(s -> {
            if (listaGiocatori) {
                String queryGiocatori = "select g.nome_cognome, g.id, g.numero_ammonizioni from giocatore g where g.id_squadra = ?";
                List<Map<String, Object>> giocatoriList = jdbcTemplate.queryForList(queryGiocatori, s.getIdSquadra());
                Set<GiocatoriModel> giocatoriModelSet = new HashSet<>();
                giocatoriList.forEach(giocatoreMap -> {
                    GiocatoriModel giocatoriModel = new GiocatoriModel();
                    giocatoriModel.setNumeroAmmonizioni((Integer) giocatoreMap.get("numero_ammonizioni"));
                    giocatoriModel.setIdGiocatore((Integer) giocatoreMap.get("id"));
                    giocatoriModel.setNomeCognome((String) giocatoreMap.get("nome_cognome"));
                    giocatoriModelSet.add(giocatoriModel);
                });
                s.setGiocatori(giocatoriModelSet);
            }
        });
        return squadraModels;
    }

    @Override
    public Optional<SquadraModel> readForName(String nome) {
        String query = "select * from squadra where nome = :nomeSquadra";
        try {
            Map<String, Object> squadraMap = jdbcTemplate.queryForMap(query, new Object[]{nome});
            SquadraModel squadraModel = new SquadraModel();
            squadraModel.setIdSquadra(((Integer) squadraMap.get("id")));
            squadraModel.setNome(((String) squadraMap.get("nome")));
            squadraModel.setColoriSociali((String) squadraMap.get("colori_sociali"));
            return Optional.of(squadraModel);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
