package it.reactive.torneoDemo.repository.dao.implement.prepareStatement;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperGiocatore;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile(DaoProfile.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class SquadraPS implements DaoSquadra {

    @Autowired
    ConnesioneDb cn;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    GiocatorePS giocatorePS;

    @Autowired
    TifoseriaPS tifoseriaPS;

    @Override
    public SquadraModel create(SquadraDTO squadraDTO) throws SQLException {
        SquadraModel squadra = new SquadraModel();

        ResultSet rs;
        Connection con;
        PreparedStatement statement;

        String query = "insert into squadra ( nome, colori_sociali )"
                + " values(?, ?)";
        PreparedStatement ps;
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            statement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, Utility.formattaStringaPerDb(squadraDTO.getNome()));
            statement.setString(2, Utility.formattaStringaPerDb(squadraDTO.getColoriSociali()));
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                squadra.setIdSquadra(generatedId);
                squadra.setColoriSociali(squadraDTO.getColoriSociali());
                squadra.setNome(squadraDTO.getNome());
            }
            if (con != null) {
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return squadra;
    }

    @Override
    @Transactional
    public void delete(int id) throws SQLException {
        Connection connection = cn.init();
        tifoseriaPS.delete(id);
        giocatorePS.delete(id);
        String deleteSquadraTorneoQuery = "delete from squadra_torneo where id_squadra = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteSquadraTorneoQuery)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String deleteSquadraQuery = "delete from squadra where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSquadraQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<SquadraModel> findById(int id) {
        String query = "select s.* from squadra s where s.id = ?";
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
        String querySquadreTifoseria = "select s.*, t.*, t.id as id_tifoseria FROM squadra s LEFT JOIN tifoseria t ON t.id_squadra = s.id";
        PreparedStatement pr = connection.prepareStatement(querySquadreTifoseria);
        ResultSet rs = pr.executeQuery();
        while (rs.next()) {
            SquadraModel squadraModel = MapperSquadra.rsToModelWithTifoseria(rs);
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
        String query = "select * from squadra where nome = ?";
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

    @Override
    public List<Integer> recuperoTornei(int idSquadra) throws SQLException {
        Connection connection = cn.init();
        List<Integer> idTonei = new ArrayList<>();
        String query = "select t.id from torneo t join squadra_torneo st on t.id = st.id_torneo where st.id_squadra = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idSquadra);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                idTonei.add(rs.getInt("id"));
            }
        }
        return idTonei;
    }
}
