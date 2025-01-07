package it.reactive.torneoDemo.repository.dao.implement.querryPsc;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.dto.resource.Trasferimenti;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
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
import org.springframework.web.client.RestTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@Profile(DbProfile.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class GiocatoreJdbc implements DaoGiocatori {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        Integer generatedId = keyHolder.getKey().intValue();
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        giocatoriModel.setIdGiocatore(generatedId);
        giocatoriModel.setNomeCognome(giocatoreDTO.getNomeCognome());
        giocatoriModel.setNumeroAmmonizioni(0);
        return giocatoriModel;
    }


    @Override
    public HashSet<GiocatoriModel> readGiocatoriWithIdSquadra(int id) {
        String query = "select g.*, s.nome from giocatore g join squadra s on g.id_squadra = s.id where g.id_squadra = ?";
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            return ps;
        };
        ResultSetExtractor<HashSet<GiocatoriModel>> rse = rs -> {
            HashSet<GiocatoriModel> giocatori = new HashSet<>();
            while (rs.next()) {
                GiocatoriModel giocatore = MapperGiocatore.rsToModel(rs);
                giocatori.add(giocatore);
            }
            return giocatori;
        };
        return jdbcTemplate.query(psc, rse);
    }


    @Override
    public Optional<GiocatoriModel> readForName(String nome) {
        String query = "select * from giocatore where nome_cognome = ?";
        PreparedStatementCreator psc = con -> {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nome);
            return ps;
        };
        ResultSetExtractor<Optional<GiocatoriModel>> rse = rs -> {
            if (rs.next()) {
                GiocatoriModel giocatore = MapperGiocatore.rsToModel(rs);
                return Optional.of(giocatore);
            }
            return Optional.empty();
        };
        return jdbcTemplate.query(psc, rse);

    }

    @Override
    public Optional<GiocatoriModel> readForId(int id) {
        String query = "select * from giocatore where id = ?";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr;
        };

        ResultSetExtractor<Optional<GiocatoriModel>> rse = rs -> {
            if (rs.next()){
                GiocatoriModel giocatoriModel = MapperGiocatore.rsToModel(rs);
                return Optional.of(giocatoriModel);
            }
            return Optional.empty();
        };
        return jdbcTemplate.query(psc, rse);
    }

    @Override
    public void incrementaAmmonizioni(int idGiocatore) throws SQLException {
        String query = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ?";
        jdbcTemplate.update(query, idGiocatore);
    }

    @Override
    public Set<Trasferimenti> trasferimenti(String nome) {
        String url = "http://85.235.148.177:8872/transfer/" + nome;
        RestTemplate restTemplate = new RestTemplate();
        Set<Trasferimenti> trasferimentiSet = new HashSet<>();
        Trasferimenti[] trasferimentiArray = restTemplate.getForObject(url, Trasferimenti[].class);
        if (trasferimentiArray != null) {
            Collections.addAll(trasferimentiSet, trasferimentiArray);
        }
        return trasferimentiSet;
    }
}

