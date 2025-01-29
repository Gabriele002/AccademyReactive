package it.reactive.accademy.demoTorneoBatch.tasklet;

import it.reactive.accademy.demoTorneoBatch.model.GiocatoriModel;
import it.reactive.accademy.demoTorneoBatch.model.SquadraModel;
import it.reactive.accademy.demoTorneoBatch.model.TifoseriaModel;
import it.reactive.accademy.demoTorneoBatch.model.TorneoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static it.reactive.accademy.utility.Costanti.ENTITY_MANAGER_FACTORY;
import static it.reactive.accademy.utility.Costanti.TASKLET_DELETE;

@Configuration
public class TaskletDelete {


    private final int RESET_SEQUENZA = 1;

    @Bean(TASKLET_DELETE)
    public Tasklet deleteTasklet(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                try {
                    entityManager.getTransaction().begin();

                    deleteEntity(entityManager, GiocatoriModel.class);
                    deleteEntity(entityManager, TifoseriaModel.class);
                    deleteEntity(entityManager, SquadraModel.class);
                    deleteEntity(entityManager, TorneoModel.class);

                    resetSequence(entityManager, "torneo_id_seq");
                    resetSequence(entityManager, "squadra_id_seq");
                    resetSequence(entityManager, "giocatore_id_seq");
                    resetSequence(entityManager, "tifoseria_id_seq");

                    entityManager.getTransaction().commit();
                    return RepeatStatus.FINISHED;
                } catch (Exception e) {
                    entityManager.getTransaction().rollback();
                    throw e;
                } finally {
                    if (entityManager.isOpen()) {
                        entityManager.close();
                    }
                }
            }
        };
    }

    private <T> void deleteEntity(EntityManager entityManager, Class<T> model) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> deleteCriteria = cb.createCriteriaDelete(model);
        deleteCriteria.from(model);
        entityManager.createQuery(deleteCriteria).executeUpdate();
    }

    public void resetSequence(EntityManager entityManager, String sequenceName) {
        StringBuilder sql = new StringBuilder();
        sql.append("alter sequence ");
        sql.append(sequenceName);
        sql.append(" restart with 1");
        entityManager.createNativeQuery(sql.toString()).executeUpdate();
    }

}
