package it.reactive.torneoDemo.repository.dao.implement.prepareStatmenr;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.utility.DbCostanti;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository
public class SquadraImpl implements DaoSquadra {

    @Autowired
    ConnesioneDb cn;

    @Autowired
    DbCostanti dbCostanti;

    @Override
    public SquadraModel create(SquadraDTO squadraDTO) throws SQLException {
        Connection connection = cn.init();
        SquadraModel squadra = new SquadraModel();
        String query = "insert into " + dbCostanti.SQUADRA_TABLE +
                " (" + dbCostanti.SQUADRA_NOME_COL + "," + dbCostanti.SQUADRA_COLORI_SOCIALI_COL + ") "
                + " values(?, ?)";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, Utility.formattaStringaPerDb(squadraDTO.getNome()));
            ps.setString(2, Utility.formattaStringaPerDb(squadraDTO.getColoriSociali()));

            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                squadra.setIdSquadra(generatedId);
                squadra.setColoriSociali(squadraDTO.getColoriSociali());
                squadra.setNome(squadraDTO.getNome());
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
        return squadra;
    }


    @Override
    public SquadraModel read(int id) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        String query = " select * from squadra where id = ?";
        PreparedStatement ps = cn.init().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            squadraModel = MapperSquadra.rsToModel(rs);
        }
        rs.close();
        return squadraModel;
    }


    @Override
    public SquadraModel update(int id, SquadraDTO torneoDTO) {
        return null;
    }

    @Override
    public SquadraModel delete(int id) throws SQLException {
        Connection connection = cn.init();

        String deleteTifoseriaQuery = "delete from tifoseria where id_squadra = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteTifoseriaQuery)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }

        String deleteGiocatoreQuery = "delete from giocatore where id_squadra = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteGiocatoreQuery)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }

        String deleteSquadraTorneoQuery = "delete from squadra_torneo where id_squadra = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteSquadraTorneoQuery)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }

        String deleteSquadraQuery = "delete from" + dbCostanti.SQUADRA_TABLE + "where" + dbCostanti.SQUADRA_ID_COL + "= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSquadraQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Optional<SquadraModel> findById(int id) {
        String query = "select * from squadra" + " where " + dbCostanti.SQUADRA_ID_COL + " = ?";
        Connection connection = cn.init();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SquadraModel squadraModel = MapperSquadra.rsToModel(rs);
                    return Optional.of(squadraModel);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) throws SQLException {
        Connection connection = cn.init();
        List<SquadraModel> squadre = new ArrayList<>();
        String querySquadre = "select * from squadra";
        PreparedStatement pr = connection.prepareStatement(querySquadre);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            SquadraModel squadraModel = MapperSquadra.rsToModel(rs);
            if (listaGiocatori) {
                String queryGiocatori = "select g.nome_cognome, g.id, g.numero_ammonizioni from giocatore g where g.id_squadra = ?";
                PreparedStatement ps = connection.prepareStatement(queryGiocatori);
                ps.setInt(1, squadraModel.getIdSquadra());
                try (ResultSet rsGiocatori = ps.executeQuery()) {
                    while (rsGiocatori.next()) {
                        GiocatoriModel giocatoriModel = MapperGiocatore.rsToModel(rsGiocatori);
                        squadraModel.getGiocatori().add(giocatoriModel);
                    }
                }
            }
            squadre.add(squadraModel);
        }

        return squadre;
    }

    @Override
    public Optional<SquadraModel> readForName(String nome) {
        String query = "select * from squadra where " + dbCostanti.SQUADRA_NOME_COL + "= ?";
        Connection connection = cn.init();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SquadraModel squadraModel = MapperSquadra.rsToModel(rs);
                return Optional.of(squadraModel);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
