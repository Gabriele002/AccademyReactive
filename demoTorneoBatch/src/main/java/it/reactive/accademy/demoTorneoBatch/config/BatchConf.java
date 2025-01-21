package it.reactive.accademy.demoTorneoBatch.config;

import it.reactive.accademy.demoTorneoBatch.dto.*;
import it.reactive.accademy.demoTorneoBatch.model.GiocatoriModel;
import it.reactive.accademy.demoTorneoBatch.model.SquadraModel;
import it.reactive.accademy.demoTorneoBatch.model.TifoseriaModel;
import it.reactive.accademy.demoTorneoBatch.model.TorneoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
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
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.*;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class BatchConf {
    public final static String JOB_ELIMINO_TORNEO = "Job-delete";
    public final static String STEP_DELETE = "Step-delete";
    public static final String TASKLET_DELETE = "TASKLET_DELETE";
    public static final String ENTITY_MENAGER = "ENTITY_MENAGER";
    public static final String ENTITY_MANAGER_FACTORY = "entityManagerFactory";
    public static final String STEP_INSERT = "STEP_INSERT";
    public static final String FILE_READER = "FILE_READER";
    public static final String WRITER_INSERT = "WRITER_READER";
    public static final String CLASSIFAIER = "CLASSIFAIER";
    public static final String WRITER_SQUADRA = "WRITER_SQUADRA";
    public static final String WRITER_GIOCATORI = "WRITER_GIOCATORI";
    public static final String WRITER_SQUADRA_TORNEO = "WRITER_SQUADRA_TORNEO";
    private static final String STEP_FILE_CSV = "STEP_FILE_CSV";
    public static final String READER_GIOCATORE_COMPLETO = "READER_GIOCATORE_COMPLETO";
    public static final String WRITER_GIOCATORE_CSV = "WRITER_GIOCATORE_CSV";

    Logger log = LoggerFactory.getLogger(BatchConf.class);

    @Bean(JOB_ELIMINO_TORNEO)
    public Job jobDeleteTorneoDemo(JobRepository jobRepository,
                                   @Qualifier(STEP_DELETE) Step delete,
                                   @Qualifier(STEP_INSERT) Step insert,
                                   @Qualifier(STEP_FILE_CSV) Step fileCsv) {
        return new JobBuilder(JOB_ELIMINO_TORNEO, jobRepository)
                .start(delete)
                .next(insert)
                .next(fileCsv)
                .build();
    }

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

    @Bean(TASKLET_DELETE)
    public Tasklet deleteTasklet(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                try {
                    entityManager.getTransaction().begin();

                    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
                    CriteriaDelete<GiocatoriModel> deleteGiocatori = cb.createCriteriaDelete(GiocatoriModel.class);
                    deleteGiocatori.from(GiocatoriModel.class);
                    entityManager.createQuery(deleteGiocatori).executeUpdate();

                    CriteriaDelete<TifoseriaModel> deleteTifoseria = cb.createCriteriaDelete(TifoseriaModel.class);
                    deleteTifoseria.from(TifoseriaModel.class);
                    entityManager.createQuery(deleteTifoseria).executeUpdate();

                    CriteriaDelete<SquadraModel> deleteSquadra = cb.createCriteriaDelete(SquadraModel.class);
                    deleteSquadra.from(SquadraModel.class);
                    entityManager.createQuery(deleteSquadra).executeUpdate();

                    CriteriaDelete<TorneoModel> deleteTorneo = cb.createCriteriaDelete(TorneoModel.class);
                    deleteTorneo.from(TorneoModel.class);
                    entityManager.createQuery(deleteTorneo).executeUpdate();
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

    @Bean(FILE_READER)
    public FlatFileItemReader<List<String>> readerTorne() {
        FixedLengthTokenizer fixedLengthTokenizer = new FixedLengthTokenizer();
        fixedLengthTokenizer.setColumns(new Range(1, 2), new Range(3, 102), new Range(103, 202), new Range(203));
        FieldSetMapper<List<String>> record = new FieldSetMapper<List<String>>() {
            @Override
            public List<String> mapFieldSet(FieldSet fieldSet) {
                List<String> valoriRecord = new ArrayList<>();
                valoriRecord.add(fieldSet.readString(0));
                valoriRecord.add(fieldSet.readString(1));
                valoriRecord.add(fieldSet.readString(2));
                valoriRecord.add(fieldSet.readString(3));
                return valoriRecord;
            }
        };
        return new FlatFileItemReaderBuilder<List<String>>()
                .name(FILE_READER)
                .lineTokenizer(fixedLengthTokenizer)
                .resource(new FileSystemResource("src/main/resources/resurce/batchtorneo.txt"))
                .fieldSetMapper(record)
                .build();
    }


    @Bean(WRITER_INSERT)
    public ClassifierCompositeItemWriter<TipoRecord> writerInsert(@Qualifier(ConfigurationDataSource.TORNEO_DATASOURCE) DataSource dataSource,
                                                                  @Qualifier(WRITER_SQUADRA) ItemWriter itemStreamWriterSquadra,
                                                                  @Qualifier(WRITER_GIOCATORI) ItemWriter itemWriterGiocatori,
                                                                  @Qualifier(WRITER_SQUADRA_TORNEO) ItemWriter itemWriterSquadraTorneo) {
        Classifier<TipoRecord, ItemWriter<? super TipoRecord>> classifier = tipoRecord -> {
            if (tipoRecord instanceof TorneoDto) {
                return new JdbcBatchItemWriterBuilder<>().dataSource(dataSource)
                        .itemPreparedStatementSetter((item, ps) -> {
                            TorneoDto torneoDTO = (TorneoDto) item;
                            ps.setString(1, torneoDTO.getNome());
                        }).sql("insert into torneo(nome_torneo) values (?)")
                        .build();

            } else if (tipoRecord instanceof SquadraDto) {
                return itemStreamWriterSquadra;
            } else if (tipoRecord instanceof GiocatoreDto) {
                return itemWriterGiocatori;
            } else if (tipoRecord instanceof SquadraTorneoDto) {
                return itemWriterSquadraTorneo;
            }
            throw new RuntimeException("TipoRecordFile non gestito: " + tipoRecord.getClass().getSimpleName());
        };

        ClassifierCompositeItemWriter<TipoRecord> classifierWriter = new ClassifierCompositeItemWriter<>();
        classifierWriter.setClassifier(classifier);
        return classifierWriter;
    }


    @Bean(READER_GIOCATORE_COMPLETO)
    public JdbcCursorItemReader<GiocatoreConSquadra> readerGiocatoreCompleto(@Qualifier(ConfigurationDataSource.TORNEO_DATASOURCE) DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<GiocatoreConSquadra>()
                .name(READER_GIOCATORE_COMPLETO)
                .dataSource(dataSource)
                .sql("select g.*, s.nome AS nome_squadra, s.colori_sociali, t.nome_tifoseria " +
                        "from giocatore g " +
                        "join squadra s on g.id_squadra = s.id " +
                        "left join tifoseria t on t.id_squadra = s.id")
                .rowMapper((rs, rowNum) -> {
                    GiocatoreConSquadra giocatoreConSquadra = new GiocatoreConSquadra();
                    giocatoreConSquadra.setIdGiocatore(rs.getInt("id"));
                    giocatoreConSquadra.setNomeCognome(rs.getString("nome_cognome"));
                    giocatoreConSquadra.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
                    giocatoreConSquadra.setNomeSquadra(rs.getString("nome_squadra"));
                    giocatoreConSquadra.setColoriSociali(rs.getString("colori_sociali"));
                    giocatoreConSquadra.setNomeTifoseria(rs.getString("nome_tifoseria"));
                    return giocatoreConSquadra;
                })
                .build();
    }



    @Bean(WRITER_GIOCATORE_CSV)
    public ItemStreamWriter<GiocatoreConSquadra> salutoItemWriter() {
        DelimitedLineAggregator<GiocatoreConSquadra> aggregator = new DelimitedLineAggregator<>();
        aggregator.setDelimiter(";");

        BeanWrapperFieldExtractor<GiocatoreConSquadra> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"idGiocatore", "nomeCognome", "numeroAmmonizioni", "nomeSquadra", "coloriSociali", "nomeTifoseria"});
        aggregator.setFieldExtractor(fieldExtractor);

        return new FlatFileItemWriterBuilder<GiocatoreConSquadra>()
                .name(WRITER_GIOCATORE_CSV)
                .resource(new FileSystemResource("src/main/resources/resurce/prova.csv"))
                .lineAggregator(aggregator)
                .build();
    }


// Prova conessione con jdbc

//    @Bean(JDBC)
//    public Tasklet taskletJDBC (@Qualifier("JDBC_TEMPLATE")JdbcTemplate jdbcTemplate){
//        Tasklet tasklet = new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                String sql = "select * from squadra";
//                List<Map<String, Object>> squadraModels = jdbcTemplate.queryForList(sql);
//                List<SquadraModel> squadraModels1 = new ArrayList<>();
//                squadraModels.forEach(stringObjectMap -> {
//                            SquadraModel squadraModel = new SquadraModel();
//                            squadraModel.setIdSquadra((Integer) stringObjectMap.get("id"));
//                            squadraModel.setNome((String) stringObjectMap.get("nome"));
//                            squadraModels1.add(squadraModel);
//                        });
//                squadraModels1.forEach(squadraModel -> log.info(squadraModel.getNome()));
//                return RepeatStatus.FINISHED;
//            }
//        };
//        return tasklet;
//    }
}
