package it.reactive.torneoDemo.repository.dao.implement.statement;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.DbCostanti;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile(DaoProfile.TORNEO_DAO_JDBC_STATEMENT)
public class SquadraStat implements DaoSquadra {
    @Autowired
    ConnesioneDb cn;

    @Autowired
    DbCostanti dbCostanti;

    @Autowired
    TifoseriaStat tifoseriaStat;

    @Autowired
    GiocatoreStat giocatoreStat;

    @Autowired
    PlatformTransactionManager transactionManager;


    @Override
    public SquadraModel create(SquadraDTO squadraDTO) throws SQLException {
        Connection connection = null;
        ResultSet rs;
        PreparedStatement statement;

        SquadraModel squadra = new SquadraModel();
        String query = "insert into squadra(" + dbCostanti.SQUADRA_NOME_COL + "," + dbCostanti.SQUADRA_COLORI_SOCIALI_COL + ") "
                + " values('" + Utility.formattaStringaPerDb(squadraDTO.getNome()) + "', '"
                + Utility.formattaStringaPerDb(squadraDTO.getColoriSociali()) + "')";

        PreparedStatement ps;
        try {
            connection = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                squadra.setIdSquadra(generatedId);
                squadra.setColoriSociali(squadraDTO.getColoriSociali());
                squadra.setNome(squadraDTO.getNome());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        }
        return squadra;
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = cn.init();
        tifoseriaStat.delete(id);
        giocatoreStat.delete(id);

        String deleteSquadraTorneoQuery = "delete from squadra_torneo where id_squadra = " + id;
        try (PreparedStatement ps = connection.prepareStatement(deleteSquadraTorneoQuery)) {
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
        String deleteSquadraQuery = "delete from squadra where id = " + id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSquadraQuery);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<SquadraModel> findById(int id) {
        String query = "select s.* from squadra s where s.id = " + id;
        Connection connection = cn.init();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
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

    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) throws SQLException {
        Connection connection = cn.init();
        List<SquadraModel> squadre = new ArrayList<>();

        String querySquadreTifoseria = "select s.*, t.*, t.id as id_tifoseria FROM squadra s LEFT JOIN tifoseria t ON t.id_squadra = s.id";
        PreparedStatement pr = connection.prepareStatement(querySquadreTifoseria);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            SquadraModel squadraModel = MapperSquadra.rsToModelWithTifoseria(rs);
            if (listaGiocatori) {
                String queryGiocatori = "select g.nome_cognome, g.id, g.numero_ammonizioni from giocatore g where g.id_squadra = " + squadraModel.getIdSquadra();
                PreparedStatement ps = connection.prepareStatement(queryGiocatori);
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
        String query = "select * from squadra where nome = '" + nome + "'";
        Connection connection = cn.init();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
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

    @Override
    public List<Integer> recuperoTornei(int idSquadra) throws SQLException {
        Connection connection = cn.init();
        List<Integer> idTornei = new ArrayList<>();
        String query = "select t.id from torneo t join squadra_torneo st on t.id = st.id_torneo where st.id_squadra = " + idSquadra;
        PreparedStatement ps = connection.prepareStatement(query);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                idTornei.add(rs.getInt("id"));
            }
        }
        return idTornei;
    }
}
