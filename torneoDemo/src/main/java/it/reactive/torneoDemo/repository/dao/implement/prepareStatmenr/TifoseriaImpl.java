package it.reactive.torneoDemo.repository.dao.implement.prepareStatmenr;


import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.mapper.MapperTifoseria;
import it.reactive.torneoDemo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class TifoseriaImpl implements DaoTifoseria {

    @Autowired
    ConnesioneDb cn;

    @Override
    public TifoseriaModel create(TifoseriaDTO tifoseriaDTO, int id) throws SQLException {
        Connection co = cn.init();
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        String createGiocatore = "insert into tifoseria (nome_tifoseria,id_squadra) values (?, ?)";
        try {
            PreparedStatement ps = co.prepareStatement(createGiocatore, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, Utility.formattaStringaPerDb(tifoseriaDTO.getNomeTifoseria()));
            ps.setInt(2, id);
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
        String queryUpdate = "update tifoseria SET nome_tifoseria = ? WHERE id_squadra = ?";
        Connection connection = cn.init();
        try {
            PreparedStatement prUpdate = connection.prepareStatement(queryUpdate);
            prUpdate.setString(1, tifoseriaDTO.getNomeTifoseria());
            prUpdate.setInt(2, idSquadra);
            prUpdate.executeUpdate();
            TifoseriaModel tifoseriaModel = new TifoseriaModel();
            tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
            connection.commit();
            return tifoseriaModel;
        } catch (SQLException e) {
            connection.rollback();
            throw new SQLException(e);
        }
    }

}
