package it.reactive.accademy.demoTorneoBatch.step;

import it.reactive.accademy.demoTorneoBatch.dto.GiocatoreConSquadra;
import it.reactive.accademy.demoTorneoBatch.dto.TipoRecord;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

import static it.reactive.accademy.demoTorneoBatch.config.BatchConf.*;

@Configuration
public class StepForJob {


    @Bean(STEP_INSERT)
    public Step insertTorneo(JobRepository jobRepository,
                             @Qualifier(ENTITY_MENAGER) PlatformTransactionManager transactionManager,
                             @Qualifier(FILE_READER) ItemStreamReader<List<String>> itemStreamReader,
                             @Qualifier(WRITER_INSERT) ClassifierCompositeItemWriter<TipoRecord> itemWriter,
                             @Qualifier(CLASSIFAIER) ClassifierCompositeItemProcessor<List<String>, TipoRecord> classifierCompositeItemProcessor) {
        return new StepBuilder(STEP_INSERT, jobRepository)
                .<List<String>, TipoRecord>chunk(2, transactionManager)
                .reader(itemStreamReader)
                .processor(classifierCompositeItemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean(STEP_DELETE)
    public Step deleteTorneo(JobRepository jobRepository,
                             @Qualifier(ENTITY_MENAGER) PlatformTransactionManager transactionManager,
                             @Qualifier(TASKLET_DELETE) Tasklet tasklet) {
        return new StepBuilder(STEP_DELETE, jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }


    @Bean(STEP_FILE_CSV)
    public Step scriviFileCsv(JobRepository jobRepository,
                              @Qualifier(ENTITY_MENAGER) PlatformTransactionManager transactionManager,
                              @Qualifier(READER_GIOCATORE_COMPLETO) ItemStreamReader<GiocatoreConSquadra> itemStreamReader,
                              @Qualifier(WRITER_GIOCATORE_CSV) ItemStreamWriter<GiocatoreConSquadra> itemStreamWriter) {
        return new StepBuilder(STEP_FILE_CSV, jobRepository)
                .<GiocatoreConSquadra, GiocatoreConSquadra>chunk(2, transactionManager)
                .reader(itemStreamReader)
                .writer(itemStreamWriter)
                .build();
    }
}
