package it.reactive.torneoDemo.repository.dao.implement.jdbcQueryForX;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry.rowMapper.CustomRowMapperGiocatore;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JDBC_QUERY_FOR_X)
public class GiocatoreJdbcQuerryForX implements DaoGiocatori {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException {
        String createGiocatore = "insert into giocatore (nome_cognome, id_squadra) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(createGiocatore, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, Utility.formattaStringaPerDb(giocatoreDTO.getNomeCognome()));
            ps.setInt(2, id);
            return ps;
        }, keyHolder);
        Integer generateId = (Integer) keyHolder.getKeys().get("id");
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        giocatoriModel.setIdGiocatore(generateId);
        giocatoriModel.setNomeCognome(giocatoreDTO.getNomeCognome());
        giocatoriModel.setNumeroAmmonizioni(0);
        return giocatoriModel;
    }


    @Override
    public HashSet<GiocatoriModel> readGiocatoriWithIdSquadra(int id) {
        String query = "select g.*, s.nome from giocatore g join squadra s on g.id_squadra = s.id where g.id_squadra = ?";
        List<Map<String, Object>> mapGiocatori = jdbcTemplate.queryForList(query,id);
        HashSet<GiocatoriModel> giocatori = new HashSet<>();
        mapGiocatori.forEach(stringObjectMap -> {
            GiocatoriModel giocatoriModel = new GiocatoriModel();
            giocatoriModel.setIdGiocatore((Integer) stringObjectMap.get("id"));
            giocatoriModel.setNomeCognome((String) stringObjectMap.get("nome_cognome"));
            giocatoriModel.setNumeroAmmonizioni((Integer) stringObjectMap.get("numero_ammonizioni"));
            giocatori.add(giocatoriModel);
        });
        return giocatori;
    }

    @Override
    public Optional<GiocatoriModel> readForName(String nome) {
        String query = "select * from giocatore where nome_cognome = ?";
        try {
            Map<String, Object> giocatoreMap = jdbcTemplate.queryForMap(query, new Object[]{nome});
            GiocatoriModel giocatoriModel = new GiocatoriModel();
            giocatoriModel.setIdGiocatore((Integer) giocatoreMap.get("id"));
            giocatoriModel.setNumeroAmmonizioni((Integer) giocatoreMap.get("numero_ammonizioni"));
            giocatoriModel.setNomeCognome((String) giocatoreMap.get("nome_cognome"));
            return Optional.of(giocatoriModel);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GiocatoriModel> readForId(int id) {
        String query = "select * from giocatore where id = ?";
        try {
            Map<String, Object> giocatoreMap = jdbcTemplate.queryForMap(query, new Object[]{id});
            GiocatoriModel giocatoriModel = new GiocatoriModel();
            giocatoriModel.setIdGiocatore((Integer) giocatoreMap.get("id"));
            giocatoriModel.setNumeroAmmonizioni((Integer) giocatoreMap.get("numero_ammonizioni"));
            giocatoriModel.setNomeCognome((String) giocatoreMap.get("nome_cognome"));
            return Optional.of(giocatoriModel);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void incrementaAmmonizioni(int idGiocatore) throws SQLException {
        String query = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ?";
        jdbcTemplate.update(query, idGiocatore);
    }

}

