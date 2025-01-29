package it.reactive.torneoDemo.repository.dao.implement.entityManagerBase;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.model.TifoseriaModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.utility.DaoProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Profile(DaoProfile.TORNEO_DAO_SPRING_JPA_ENTITY_MANAGER_BASE)
@Transactional
public class SquadraEntityManagerBase implements DaoSquadra {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    GiocatoreEntityManagerBase giocatoreEntityManagerBase;

    @Autowired
    TifoseriaEntityManagerBase tifoseriaEntityManagerBase;

    @Override
    public SquadraModel create(SquadraDTO squadraDTO) throws SQLException {
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setNome(squadraDTO.getNome());
        squadraModel.setColoriSociali(squadraDTO.getColoriSociali());
        entityManager.persist(squadraModel);
        return squadraModel;
    }

    @Transactional
    @Override
    public void delete(int id) throws Exception {
        entityManager.joinTransaction();
        SquadraModel squadraModel = entityManager.find(SquadraModel.class, id);
        Set<GiocatoriModel> giocatoriModelList = giocatoreEntityManagerBase.readGiocatoriWithIdSquadra(id);
        if (!giocatoriModelList.isEmpty()){
            giocatoriModelList.forEach(g -> giocatoreEntityManagerBase.delete(g.getIdGiocatore()));
        }
        Optional<TifoseriaModel> tifoseriaModel = tifoseriaEntityManagerBase.readForIdSquadra(id);
        tifoseriaModel.ifPresent(model -> tifoseriaEntityManagerBase.delete(model.getIdTifoseria()));

        String sql = "delete from squadra_torneo where id_squadra = :idSquadra";
        entityManager.createNativeQuery(sql)
                .setParameter("idSquadra", id)
                .executeUpdate();
        entityManager.remove(squadraModel);
    }

    @Override
    public Optional<SquadraModel> findById(int id) throws SQLException {
        SquadraModel squadraModel = entityManager.find(SquadraModel.class, id);
        if (squadraModel == null){
            return Optional.empty();
        }
        return Optional.of(squadraModel);
    }

    @Override
    public List<SquadraModel> readAll(boolean listaGiocatori) throws SQLException {
        List<SquadraModel> squadraModelList = entityManager.createNamedQuery("findAll", SquadraModel.class).getResultList();
        if (!listaGiocatori){
            squadraModelList.forEach(squadraModel -> {
                squadraModel.setGiocatori(new HashSet<>());
            });
        }
        return squadraModelList;
    }

    @Override
    @Transactional
    public Optional<SquadraModel> readForName(String nome) {
        entityManager.joinTransaction();
        TypedQuery<SquadraModel> query = entityManager.createNamedQuery("findByName", SquadraModel.class);
        query.setParameter("nomeSquadra", nome);
        List<SquadraModel> squadraModelList = query.getResultList();
        if (squadraModelList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(squadraModelList.get(0));
    }

    @Override
    public List<Integer> recuperoTornei(int idSquadra) throws SQLException {
        TypedQuery<Integer> query = entityManager.createNamedQuery("findTornei", Integer.class);
        query.setParameter("idSquadra", idSquadra);
        return query.getResultList();
    }

}
