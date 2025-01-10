package it.reactive.torneoDemo.repository.dao.implement.statement;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.mapper.MapperTifoseria;
import it.reactive.torneoDemo.utility.DaoProfile;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


@Repository
@Profile(DaoProfile.TORNEO_DAO_JDBC_STATEMENT)
public class TifoseriaStat implements DaoTifoseria {

    @Autowired
    ConnesioneDb cn;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Override
    public TifoseriaModel create(TifoseriaDTO tifoseriaDTO, int id) throws SQLException {
        Connection co = cn.init();
        TifoseriaModel tifoseriaModel = new TifoseriaModel();

        String createGiocatore = "insert into tifoseria (nome_tifoseria,id_squadra) values ('"
                + Utility.formattaStringaPerDb(tifoseriaDTO.getNomeTifoseria())
                + "', " + id + ")";

        try {
            PreparedStatement ps = co.prepareStatement(createGiocatore, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                tifoseriaModel.setIdTifoseria(generatedId);
                tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
            }
            co.commit();
        } catch (SQLException e) {
            co.rollback();
            throw new RuntimeException(e);
        }
        return tifoseriaModel;
    }

    @Override
    public Optional<TifoseriaModel> readForIdSquadra(int id) throws SQLException {
        String query = "select t.* from tifoseria t where id_squadra = " + id;
        Connection connection = cn.init();
        PreparedStatement pr = connection.prepareStatement(query);
        ResultSet rs = pr.executeQuery();
        if (rs.next()) {
            TifoseriaModel tifoseriaModel = MapperTifoseria.rsToModel(rs);
            return Optional.of(tifoseriaModel);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public TifoseriaModel update(TifoseriaDTO tifoseriaDTO, int idSquadra) throws SQLException {
        String queryUpdate = "UPDATE tifoseria SET nome_tifoseria = '"
                + tifoseriaDTO.getNomeTifoseria()
                + "' WHERE id_squadra = " + idSquadra;

        String querySelect = "SELECT * FROM tifoseria WHERE id_squadra = " + idSquadra;

        Connection connection = cn.init();
        try {
            PreparedStatement prUpdate = connection.prepareStatement(queryUpdate);
            prUpdate.executeUpdate();

            PreparedStatement prSelect = connection.prepareStatement(querySelect);
            ResultSet rs = prSelect.executeQuery();

            TifoseriaModel tifoseriaModel = null;
            if (rs.next()) {
                tifoseriaModel = MapperTifoseria.rsToModel(rs);
            }
            connection.commit();

            return tifoseriaModel;
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = cn.init();
        String deleteTifoseriaQuery = "delete from tifoseria where id_squadra = " + id;
        try (PreparedStatement ps = connection.prepareStatement(deleteTifoseriaQuery)) {
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
