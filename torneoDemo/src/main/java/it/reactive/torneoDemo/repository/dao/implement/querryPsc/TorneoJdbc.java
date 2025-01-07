package it.reactive.torneoDemo.repository.dao.implement.querryPsc;

import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.TorneoModel;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.utility.DbProfile;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Profile(DbProfile.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class TorneoJdbc implements DaoTorneo {
    @Override
    public TorneoModel create(TorneoDTO torneoDTO) throws SQLException {
        return null;
    }

    @Override
    public Optional<TorneoModel> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void delete(int id) throws SQLException {

    }

    @Override
    public TorneoModel findByIdWithSquadra(int id) throws SQLException {
        return null;
    }

    @Override
    public void aggiungoSquadraAlTorneo(int idSquadra, int idTorneo) throws SQLException {

    }

    @Override
    public List<TorneoModel> getAllTorneo() throws SQLException {
        return Collections.emptyList();
    }

    @Override
    public List<Integer> readTorniSquadra(int idTorneo) {
        return Collections.emptyList();
    }
}
