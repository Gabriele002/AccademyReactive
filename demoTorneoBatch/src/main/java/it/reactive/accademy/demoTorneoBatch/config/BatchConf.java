package it.reactive.accademy.demoTorneoBatch.config;

import it.reactive.accademy.demoTorneoBatch.config.model.GiocatoriModel;
import it.reactive.accademy.demoTorneoBatch.config.model.SquadraModel;
import it.reactive.accademy.demoTorneoBatch.config.model.TifoseriaModel;
import it.reactive.accademy.demoTorneoBatch.config.model.TorneoModel;
import it.reactive.accademy.demoTorneoBatch.config.model.dto.*;
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
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    Logger log = LoggerFactory.getLogger(BatchConf.class);

    @Bean(JOB_ELIMINO_TORNEO)
    public Job jobDeleteTorneoDemo(JobRepository jobRepository,
                                   @Qualifier(STEP_DELETE) Step delete,
                                   @Qualifier(STEP_INSERT) Step insert) {
        return new JobBuilder(JOB_ELIMINO_TORNEO, jobRepository)
                .start(delete)
                .next(insert)
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
            } else if (tipoRecord instanceof  GiocatoreDto) {
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

    @Bean(CLASSIFAIER)
    public ClassifierCompositeItemProcessor<List<String>, TipoRecord> classifierCompositeItemProcessor() {
        Classifier<List<String>, ItemProcessor<?, ? extends TipoRecord>> classifier = new Classifier<>() {
            @Override
            public ItemProcessor<?, ? extends TipoRecord> classify(List<String> listaAttributi) {
                String tipo = listaAttributi.get(0);
                return switch (tipo) {
                    case "TO" -> new ItemProcessor<List<String>, TipoRecord>() {
                        @Override
                        public TipoRecord process(List<String> item) throws Exception {
                            TorneoDto torneoDto = new TorneoDto();
                            torneoDto.setNome(listaAttributi.get(1));
                            return torneoDto;
                        }
                    };
                    case "SQ" -> new ItemProcessor<List<String>, TipoRecord>() {
                        @Override
                        public TipoRecord process(List<String> item) throws Exception {
                            SquadraDto squadraDto = new SquadraDto();
                            squadraDto.setNome(listaAttributi.get(1));
                            squadraDto.setColori_sociali(listaAttributi.get(2));
                            squadraDto.setTifoseriaNome(listaAttributi.get(3));
                            return squadraDto;
                        }
                    };
                    case "GI" -> new ItemProcessor<List<String>, TipoRecord>() {
                        @Override
                        public TipoRecord process(List<String> item) throws Exception {
                            GiocatoreDto giocatoreDto = new GiocatoreDto();
                            String nomeCognome = listaAttributi.get(1).replaceAll("\\s+", " ").trim();
                            giocatoreDto.setNomeCognome(nomeCognome);
                            giocatoreDto.setNomeSquadra(listaAttributi.get(2));
                            return giocatoreDto;
                        }
                    };
                    case "TS" -> new ItemProcessor<List<String>, TipoRecord>() {
                        @Override
                        public TipoRecord process(List<String> item) throws Exception {
                            SquadraTorneoDto squadraTorneoDto = new SquadraTorneoDto();
                            squadraTorneoDto.setNomeSquadra(listaAttributi.get(2));
                            squadraTorneoDto.setNomeTorneo(listaAttributi.get(1));
                            return squadraTorneoDto;
                        }
                    };
                    default -> new ItemProcessor<String, TipoRecord>() {
                        @Override
                        public TipoRecord process(String riga) throws Exception {
                            return null;
                        }
                    };
                };
            }
        };
        ClassifierCompositeItemProcessor<List<String>, TipoRecord> classifierProcessor = new ClassifierCompositeItemProcessor<>();
        classifierProcessor.setClassifier(classifier);
        return classifierProcessor;
    }


    @Bean(WRITER_SQUADRA)
    public ItemStreamWriter<TipoRecord> writerSquadra(@Qualifier(ConfigurationDataSource.TORNEO_DATASOURCE) DataSource dataSource) {
        return new ItemStreamWriter<TipoRecord>() {
            @Override
            public void write(Chunk<? extends TipoRecord> chunk) throws Exception {
                chunk.forEach(tipoFile -> {
                    SquadraDto squadraDto = (SquadraDto) tipoFile;
                    try {
                        ResultSet rs = null;
                        Connection con = dataSource.getConnection();
                        PreparedStatement pr = con.prepareStatement("insert into squadra(nome,colori_sociali) values(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        pr.setString(1, squadraDto.getNome());
                        pr.setString(2, squadraDto.getColori_sociali());
                        int affectedRows = pr.executeUpdate();

                        if (affectedRows > 0) {
                            rs = pr.getGeneratedKeys();
                            if (rs.next()) {
                                int id = rs.getInt("id");
                                squadraDto.setId(id);
                            }
                        }
                        if (!squadraDto.getTifoseriaNome().equals("-")) {
                            pr = con.prepareStatement("insert into tifoseria(nome_tifoseria, id_squadra) values(?,?)");
                            pr.setString(1, squadraDto.getTifoseriaNome());
                            pr.setInt(2, squadraDto.getId());
                            pr.executeUpdate();
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
            }
        };
    }


    @Bean(WRITER_GIOCATORI)
    public ItemStreamWriter<TipoRecord> writerGiocatori(@Qualifier(ConfigurationDataSource.TORNEO_DATASOURCE) DataSource dataSource) {
        return new ItemStreamWriter<TipoRecord>() {
            @Override
            public void write(Chunk<? extends TipoRecord> chunk) throws Exception {
                chunk.forEach(tipoFile -> {
                    GiocatoreDto giocatoreDto = (GiocatoreDto) tipoFile;
                    try {
                        ResultSet rs = null;
                        Connection con = dataSource.getConnection();
                        PreparedStatement pr = con.prepareStatement("select s.id from squadra s where nome = ?");
                        pr.setString(1, giocatoreDto.getNomeSquadra());
                        rs = pr.executeQuery();
                        int id = 0;
                        if (rs.next()) {
                            id = rs.getInt("id");
                        }
                        PreparedStatement prInsert = con.prepareStatement("insert into giocatore(nome_cognome, numero_ammonizioni, id_squadra) values (?,?,?)");
                        prInsert.setString(1, giocatoreDto.getNomeCognome());
                        prInsert.setInt(2, 0);
                        prInsert.setInt(3, id);
                        prInsert.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
            }
        };
    }


    @Bean(WRITER_SQUADRA_TORNEO)
    public ItemStreamWriter<TipoRecord> writerSquadraTorneo(@Qualifier(ConfigurationDataSource.TORNEO_DATASOURCE) DataSource dataSource) {
        return new ItemStreamWriter<TipoRecord>() {
            @Override
            public void write(Chunk<? extends TipoRecord> chunk) throws Exception {
                chunk.forEach(tipoFile -> {
                    SquadraTorneoDto squadraTorneoDto = (SquadraTorneoDto) tipoFile;
                    try {
                        ResultSet rs = null;
                        Connection con = dataSource.getConnection();
                        PreparedStatement prSquadra = con.prepareStatement("select s.id from squadra s where nome = ?");
                        prSquadra.setString(1, squadraTorneoDto.getNomeSquadra());
                        rs = prSquadra.executeQuery();
                        int idSquadra = 0;
                        if (rs.next()) {
                            idSquadra = rs.getInt("id");
                        }
                        PreparedStatement prTorneo = con.prepareStatement("select t.id from torneo t where nome_torneo = ?");
                        prTorneo.setString(1, squadraTorneoDto.getNomeTorneo());
                        rs = prTorneo.executeQuery();
                        int idTorneo = 0;
                        if (rs.next()) {
                            idTorneo = rs.getInt("id");
                        }
                        PreparedStatement prInsert = con.prepareStatement("insert into squadra_torneo(id_squadra, id_torneo) values (?,?)");
                        prInsert.setInt(1, idSquadra);
                        prInsert.setInt(2, idTorneo);
                        prInsert.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        };
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
