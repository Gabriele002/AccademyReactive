package it.reactive.torneoDemo.repository.dao.implement.Statment;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.dto.resource.Trasferimenti;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemo.utility.DbProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.sql.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@Profile(DbProfile.TORNEO_DAO_JDBC_STATEMENT)
public class GiocatoreStat implements DaoGiocatori {

    @Autowired
    ConnesioneDb cn;

    @Override
    public GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException {
        Connection co = cn.init();
        GiocatoriModel giocatoriModel = new GiocatoriModel();

        String createGiocatore = "insert into giocatore (nome_cognome,id_squadra) values ('"
                + Utility.formattaStringaPerDb(giocatoreDTO.getNomeCognome())
                + "', " + id + ")";

        try {
            PreparedStatement ps = co.prepareStatement(createGiocatore, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                giocatoriModel.setIdGiocatore(generatedId);
                giocatoriModel.setNomeCognome(giocatoreDTO.getNomeCognome());
                giocatoriModel.setNumeroAmmonizioni(0);
            }
            co.commit();
        } catch (SQLException e) {
            co.rollback();
            throw new RuntimeException(e);
        }
        return giocatoriModel;
    }

    @Override
    public HashSet<GiocatoriModel> readGiocatoriWithIdSquadra(int id) {
        HashSet<GiocatoriModel> giocatori = new HashSet<>();
        GiocatoriModel giocatore;
        String query = "select g.*, s.nome from giocatore g join squadra s on g.id_squadra = s.id where g.id_squadra = " + id;
        PreparedStatement ps = null;
        try {
            ps = cn.init().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                giocatore = MapperGiocatore.rsToModel(rs);
                giocatori.add(giocatore);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return giocatori;
    }

    @Override
    public Optional<GiocatoriModel> readForName(String nome) {
        String query = "select * from giocatore where nome_cognome = '" + nome + "'";
        Connection connection = cn.init();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                GiocatoriModel giocatoriModel = MapperGiocatore.rsToModel(rs);
                return Optional.of(giocatoriModel);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<GiocatoriModel> readForId(int id) {
        String query = "select * from giocatore where id = " + id;
        Connection connection = cn.init();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                GiocatoriModel giocatoriModel = MapperGiocatore.rsToModel(rs);
                return Optional.of(giocatoriModel);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void incrementaAmmonizioni(int idGiocatore) throws SQLException {
        String query = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = " + idGiocatore;

        Connection connection = cn.init();
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        }
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
