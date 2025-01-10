package it.reactive.torneoDemo.repository.dao.implement.prepareStatement;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;



@Repository
@Profile(DaoProfile.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class GiocatorePS implements DaoGiocatori {

    @Autowired
    ConnesioneDb cn;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Override
    public GiocatoriModel create(GiocatoreDTO giocatoreDTO, int id) throws SQLException {
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        ResultSet rs;
        Connection connection;
        PreparedStatement statement;
        String createGiocatore = "insert into giocatore (nome_cognome,id_squadra) values (?, ?)";
        try {
            connection = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            statement = connection.prepareStatement(createGiocatore, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, Utility.formattaStringaPerDb(giocatoreDTO.getNomeCognome()));
            statement.setInt(2, id);
            statement.executeUpdate();
             rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                giocatoriModel.setIdGiocatore(generatedId);
                giocatoriModel.setNomeCognome(giocatoreDTO.getNomeCognome());
                giocatoriModel.setNumeroAmmonizioni(0);
            }
            if (connection != null){
                DataSourceUtils.releaseConnection(connection, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return giocatoriModel;
    }

    @Override
    public HashSet<GiocatoriModel> readGiocatoriWithIdSquadra(int id) {
        HashSet<GiocatoriModel> giocatori = new HashSet<>();
        GiocatoriModel giocatore;
        String query = "select g.*, s.nome from giocatore g join squadra s on g.id_squadra = s.id where g.id_squadra =?";
        PreparedStatement ps = null;
        try {
            ps = cn.init().prepareStatement(query);
            ps.setInt(1, id);
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
        String query = "select * from giocatore where nome_cognome = ?";
        Connection connection = cn.init();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nome);
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
        String query = "select * from giocatore where id = ?";
        Connection connection = cn.init();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
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
        ResultSet rs;
        Connection connection;
        PreparedStatement pr;

        String query = "update giocatore set numero_ammonizioni = numero_ammonizioni + 1 where id = ?";
        try{
            connection = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            pr = connection.prepareStatement(query);
            pr.setInt(1, idGiocatore);
            pr.executeUpdate();
            if (connection != null){
                DataSourceUtils.releaseConnection(connection, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

    }

    @Override
    public void delete(int id) {
        Connection connection = cn.init();
        String deleteGiocatoreQuery = "delete from giocatore where id_squadra = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteGiocatoreQuery)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }


}
