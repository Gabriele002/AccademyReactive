package it.reactive.torneoDemo.repository.dao.implement.prepareStatement;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.mapper.MapperTifoseria;
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
import java.util.Optional;

@Repository
@Profile(DaoProfile.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class TifoseriaPS implements DaoTifoseria {

    @Autowired
    ConnesioneDb cn;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Override
    public TifoseriaModel create(TifoseriaDTO tifoseriaDTO, int id) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        ResultSet rs;
        Connection con;
        PreparedStatement statement = null;
        String createTifoseria = "insert into tifoseria (nome_tifoseria,id_squadra) values (?, ?)";
        try {
            con = DataSourceUtils.getConnection(((DataSourceTransactionManager) transactionManager).getDataSource());
            PreparedStatement ps = con.prepareStatement(createTifoseria, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, Utility.formattaStringaPerDb(tifoseriaDTO.getNomeTifoseria()));
            ps.setInt(2, id);
            ps.executeUpdate();
           rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                tifoseriaModel.setIdTifoseria(generatedId);
                tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
            }
            if (con != null){
                DataSourceUtils.releaseConnection(con, ((DataSourceTransactionManager) transactionManager).getDataSource());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tifoseriaModel;
    }

    @Override
    public Optional<TifoseriaModel> readForIdSquadra(int id) throws SQLException {
        String query = "select t.* from tifoseria t where id_squadra = ?";
        Connection connection = cn.init();
        PreparedStatement pr = connection.prepareStatement(query);
        pr.setInt(1, id);
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
        String queryUpdate = "UPDATE tifoseria SET nome_tifoseria = ? WHERE id_squadra = ?";
        String querySelect = "SELECT * FROM tifoseria WHERE id_squadra = ?";
        Connection connection = cn.init();
        try {
            PreparedStatement prUpdate = connection.prepareStatement(queryUpdate);
            prUpdate.setString(1, tifoseriaDTO.getNomeTifoseria());
            prUpdate.setInt(2, idSquadra);
            prUpdate.executeUpdate();

            PreparedStatement prSelect = connection.prepareStatement(querySelect);
            prSelect.setInt(1, idSquadra);
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
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = cn.init();
        String deleteTifoseriaQuery = "delete from tifoseria where id_squadra = ?";
        try (PreparedStatement ps = connection.prepareStatement(deleteTifoseriaQuery)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
