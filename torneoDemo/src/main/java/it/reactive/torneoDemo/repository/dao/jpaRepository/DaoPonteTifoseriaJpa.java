package it.reactive.torneoDemo.repository.dao.jpaRepository;

import it.reactive.torneoDemo.dto.in.TifoseriaDTO;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.dao.jpaRepository.repoJpa.RepoTifoseriaJpa;
import it.reactive.torneoDemo.utility.DaoProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class DaoPonteTifoseriaJpa implements DaoTifoseria {

    @Autowired
    RepoTifoseriaJpa repoTifoseriaJpa;

    @Override
    public TifoseriaModel create(TifoseriaDTO tifoseriaDTO, int id) throws SQLException {
        TifoseriaModel tifoseriaModel = new TifoseriaModel();
        tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(id);
        tifoseriaModel.setSquadra(squadraModel);
        return repoTifoseriaJpa.save(tifoseriaModel);
    }

    @Override
    public Optional<TifoseriaModel> readForIdSquadra(int idSquadra) throws SQLException {
        return repoTifoseriaJpa.findByIdSquadra(idSquadra);
    }

    @Override
    public TifoseriaModel update(TifoseriaDTO tifoseriaDTO, int id) throws SQLException {
        TifoseriaModel tifoseriaModel = repoTifoseriaJpa.findByIdSquadra(id).get();
        tifoseriaModel.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
        return repoTifoseriaJpa.save(tifoseriaModel);

    }

    @Override
    public void delete(int id) {
        repoTifoseriaJpa.deleteById(id);
    }
}
