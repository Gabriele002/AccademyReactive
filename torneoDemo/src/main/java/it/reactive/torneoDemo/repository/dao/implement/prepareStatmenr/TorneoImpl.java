package it.reactive.torneoDemo.repository.dao.implement.prepareStatmenr;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.repository.mapper.MapperTorneo;
import it.reactive.torneoDemo.utility.DbCostanti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class TorneoImpl implements DaoTorneo {

    @Autowired
    DbCostanti db;

    @Autowired
    ConnesioneDb connesioneDb;


    @Override
    public TorneoModel create(TorneoDTO torneoDTO) throws SQLException {
        String querryTorneo = "insert into " + db.TORNEO_TABLE + " (" + db.TORNEO_NOME_TORNEO_COL + ") " + "values(?)";
        Connection cn = connesioneDb.init();
        try {
            PreparedStatement pr = cn.prepareStatement(querryTorneo, PreparedStatement.RETURN_GENERATED_KEYS);
            pr.setString(1, torneoDTO.getNomeTorneo());
            TorneoModel torneoModel = new TorneoModel();
            pr.executeUpdate();
            ResultSet generatedKeys = pr.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                torneoModel.setIdTorneo(generatedId);
                torneoModel.setNomeTorneo(torneoDTO.getNomeTorneo());
            }
            cn.commit();
            return torneoModel;
        } catch (SQLException e) {
            cn.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public TorneoModel read(int id) {
        return null;
    }

    @Override
    public TorneoModel update(int id, TorneoDTO torneoDTO) {
        return null;
    }

    @Override
    public TorneoModel delete(int id) {
        return null;
    }

    @Override
    public Optional<TorneoModel> findById(int id) throws SQLException {
        String querryFind = "select * from torneo where id = ?";
        Connection con = connesioneDb.init();
        try (PreparedStatement ps = con.prepareStatement(querryFind)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TorneoModel torneoModel = MapperTorneo.rsToModel(rs);
                    return Optional.of(torneoModel);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void aggiungoSquadraAlTorneo(int idSquadra, int idTorneo) throws SQLException {
        String querryInsert = "insert into squadra_torneo (id_squadra , id_torneo) values (?, ?)";
        Connection cn = connesioneDb.init();
        try {
            PreparedStatement pr = cn.prepareStatement(querryInsert);
            pr.setInt(1,idSquadra);
            pr.setInt(2, idTorneo);
            pr.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            cn.rollback();
            throw new RuntimeException(e);
        }
    }

}
