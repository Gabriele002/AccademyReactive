package it.reactive.torneoDemo.repository.dao.implement.statement;

import it.reactive.torneoDemo.configuration.ConnesioneDb;
import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperTorneo;
import it.reactive.torneoDemo.utility.DbCostanti;
import it.reactive.torneoDemo.utility.DaoProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Profile(DaoProfile.TORNEO_DAO_JDBC_STATEMENT)
public class TorneoStat implements DaoTorneo {

    @Autowired
    ConnesioneDb connesioneDb;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Override
    public TorneoModel create(TorneoDTO torneoDTO) throws SQLException {
        String querryTorneo = "insert into torneo (nome_torneo) values('"
                + torneoDTO.getNomeTorneo() + "')";

        Connection cn = connesioneDb.init();
        try {
            PreparedStatement pr = cn.prepareStatement(querryTorneo, PreparedStatement.RETURN_GENERATED_KEYS);
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
    public void delete(int id) throws SQLException {
        String querryDeleteSquadraTorneo = "delete from squadra_torneo where id_torneo = " + id;
        String querryDelete = "delete from torneo where id = " + id;

        Connection con = connesioneDb.init();
        try {
            PreparedStatement pr = con.prepareStatement(querryDelete);
            PreparedStatement prSt = con.prepareStatement(querryDeleteSquadraTorneo);
            prSt.executeUpdate();
            pr.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public TorneoModel findByIdWithSquadra(int id) throws SQLException {
        String querryFind = "select t.*, s.id as squadra_id, s.nome as nome_squadra, s.colori_sociali " +
                "from torneo t " +
                "join squadra_torneo st on t.id = st.id_torneo " +
                "join squadra s on st.id_squadra = s.id " +
                "where t.id = " + id;

        Connection con = connesioneDb.init();
        try (PreparedStatement ps = con.prepareStatement(querryFind)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                TorneoModel torneoModel = null;
                if (rs.next()){
                    torneoModel = MapperTorneo.rsToModelWithSquadra(rs);
                }
                return torneoModel;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TorneoModel> findById(int id) {
        String querryFind = "select t.* from torneo t where t.id = " + id;

        Connection con = connesioneDb.init();
        try (PreparedStatement ps = con.prepareStatement(querryFind)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TorneoModel torneoModel = MapperTorneo.rsToModel(rs);
                return Optional.of(torneoModel);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void aggiungoSquadraAlTorneo(int idSquadra, int idTorneo) throws SQLException {
        String querryInsert = "insert into squadra_torneo (id_squadra , id_torneo) values(" + idSquadra + ", " + idTorneo + ")";

        Connection cn = connesioneDb.init();
        try {
            PreparedStatement pr = cn.prepareStatement(querryInsert);
            pr.executeUpdate();
            cn.commit();
        } catch (SQLException e) {
            cn.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TorneoModel> getAllTorneo() throws SQLException {
        String querryFind = "select t.*, s.id as id_squadra, s.nome, s.colori_sociali, tf.nome_tifoseria, tf.id as id_tifoseria " +
                "from torneo t " +
                "left join squadra_torneo st on t.id = st.id_torneo " +
                "left join squadra s on st.id_squadra = s.id " +
                "left join tifoseria tf on tf.id_squadra = s.id";

        Connection cn = connesioneDb.init();
        PreparedStatement pr = cn.prepareStatement(querryFind);
        ResultSet rs = pr.executeQuery();

        Map<Integer, TorneoModel> torneoMap = new HashMap<>();

        while (rs.next()) {
            TorneoModel torneoModel = MapperTorneo.rsToModel(rs);

            if (!torneoMap.containsKey(torneoModel.getIdTorneo())) {
                torneoMap.put(torneoModel.getIdTorneo(), torneoModel);
            }

            SquadraModel squadraModel = MapperSquadra.rsToModelIdSquadra(rs);
            torneoMap.get(torneoModel.getIdTorneo()).getSquadre().add(squadraModel);
        }

        return new ArrayList<>(torneoMap.values());
    }

    @Override
    public List<Integer> readTorniSquadra(int idTorneo) {
        String querry = "SELECT s.id " +
                "FROM squadra s " +
                "JOIN squadra_torneo st ON s.id = st.id_squadra " +
                "JOIN torneo t ON t.id = st.id_torneo " +
                "WHERE t.id = " + idTorneo;

        Connection con = connesioneDb.init();
        try {
            PreparedStatement pr = con.prepareStatement(querry);
            ResultSet rs = pr.executeQuery();

            List<Integer> squadreList = new ArrayList<>();
            while (rs.next()) {
                squadreList.add(rs.getInt("id"));
            }
            return squadreList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
