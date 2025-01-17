package it.reactive.accademy.demoTorneoBatch.config;

import it.reactive.accademy.demoTorneoBatch.config.model.GiocatoriModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConf {
    public final static String JOB_ELIMINO_TORNEO  ="Job-delete";
    public final static String STEP_DELETE ="Step-delete";


    @Autowired
    private EntityManager entityManager;

    Logger log = LoggerFactory.getLogger(BatchConf.class);


    @Bean(JOB_ELIMINO_TORNEO)
    public Job jobDeleteTorneoDemo(JobRepository jobRepository,
                                 @Qualifier(STEP_DELETE) Step delete){
        return new JobBuilder(JOB_ELIMINO_TORNEO, jobRepository)
                .start(delete)
                .build();
    }

    @Bean(STEP_DELETE)
    public Step deleteTorneo(JobRepository jobRepository,
                             @Qualifier("ENTITY_MENAGER") PlatformTransactionManager transactionManager,
                             @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new StepBuilder(STEP_DELETE, jobRepository)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        EntityManager entityManager = entityManagerFactory.createEntityManager();
                        String queryStr = "select g from GiocatoriModel g";
                        Query query = entityManager.createQuery(queryStr);
                        List<GiocatoriModel> giocatoriModelList = query.getResultList();
                        giocatoriModelList.forEach(System.out::println);
                        return RepeatStatus.FINISHED;
                    }
                }, transactionManager)
                .build();
    }



}
