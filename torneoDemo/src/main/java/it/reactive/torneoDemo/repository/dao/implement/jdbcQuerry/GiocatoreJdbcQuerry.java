package it.reactive.torneoDemo.repository.dao.implement.jdbcQuerry;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.mapper.rowMapper.CustomRowMapperGiocatore;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JDBC_QUERY)
public class GiocatoreJdbcQuerry implements DaoGiocatori {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
        List<GiocatoriModel> giocatoriModelList =
                jdbcTemplate.query(query, new CustomRowMapperGiocatore(), id);
        return new HashSet<>(giocatoriModelList);
    }


    @Override
    public Optional<GiocatoriModel> readForName(String nome) {
        String query = "select * from giocatore where nome_cognome = :nomeGiocatore";
        MapSqlParameterSource par = new MapSqlParameterSource();
        par.addValue("nomeGiocatore", nome);
        List<GiocatoriModel> giocatoriModelList = namedParameterJdbcTemplate.query(query,par, new CustomRowMapperGiocatore());
        if (giocatoriModelList.isEmpty()){
            return  Optional.empty();
        }
        GiocatoriModel giocatoriModel = giocatoriModelList.get(0);
        return Optional.of(giocatoriModel);

    }

    @Override
    public Optional<GiocatoriModel> readForId(int id) {
        String query = "select * from giocatore where id = :idGiocatore";
        MapSqlParameterSource par = new MapSqlParameterSource();
        par.addValue("idGiocatore", id);
        List<GiocatoriModel> giocatoriModelList = namedParameterJdbcTemplate.query(query,par, new CustomRowMapperGiocatore());
        if (giocatoriModelList.isEmpty()){
            return  Optional.empty();
        }
        GiocatoriModel giocatoriModel = giocatoriModelList.get(0);
        return Optional.of(giocatoriModel);
    }

    @Override
    public void incrementaAmmonizioni(int idGiocatore) throws SQLException {
        String query = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ?";
        jdbcTemplate.update(query, idGiocatore);
    }

    @Override
    public void delete(int id) {
        String deleteGiocatoreQuery = "delete from giocatore where id_squadra = ?";
        jdbcTemplate.update(deleteGiocatoreQuery, id);
    }

}

