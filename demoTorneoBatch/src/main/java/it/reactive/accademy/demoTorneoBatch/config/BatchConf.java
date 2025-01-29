package it.reactive.accademy.demoTorneoBatch.config;

import it.reactive.accademy.demoTorneoBatch.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
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
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static it.reactive.accademy.utility.Costanti.*;

@Configuration
public class BatchConf {



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
                                                                  @Qualifier(WRITER_SQUADRA_TIFOSERIA) ItemStreamWriter<TipoRecord> writerSquadraTifoseria){
        Classifier<TipoRecord, ItemWriter<? super TipoRecord>> classifier = tipoRecord -> {
            if (tipoRecord instanceof TorneoDto) {
                return new JdbcBatchItemWriterBuilder<>().dataSource(dataSource)
                        .itemPreparedStatementSetter((item, ps) -> {
                            TorneoDto torneoDTO = (TorneoDto) item;
                            ps.setString(1, torneoDTO.getNome());
                            ps.setInt(2, torneoDTO.getId());
                        }).sql("insert into torneo(nome_torneo, id) values (?, ?)")
                        .build();

            } else if (tipoRecord instanceof SquadraDto) {
                return writerSquadraTifoseria;
            } else if (tipoRecord instanceof GiocatoreDto) {
                return new JdbcBatchItemWriterBuilder<TipoRecord>()
                        .dataSource(dataSource)
                        .itemPreparedStatementSetter((item, ps) -> {
                            GiocatoreDto giocatoreDto = (GiocatoreDto) item;
                            ps.setString(1, giocatoreDto.getNomeCognome());
                            ps.setInt(2, giocatoreDto.getIdSquadra());
                        })
                        .sql("insert into giocatore(nome_cognome, id_squadra) values (?,?)")
                        .build();
            } else if (tipoRecord instanceof SquadraTorneoDto) {
                return new JdbcBatchItemWriterBuilder<TipoRecord>()
                        .dataSource(dataSource)
                        .itemPreparedStatementSetter((item, ps) -> {
                            SquadraTorneoDto squadraTorneoDto = (SquadraTorneoDto) item;
                            ps.setInt(1, squadraTorneoDto.getIdSquadra());
                            ps.setInt(2, squadraTorneoDto.getIdTorneo());
                        })
                        .sql("insert into squadra_torneo(id_squadra, id_torneo) values (?,?)")
                        .build();
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
                    String nomeTifoseria = rs.getString("nome_tifoseria");
                    giocatoreConSquadra.setNomeTifoseria(nomeTifoseria != null ? nomeTifoseria : "Nessuna tifoseria");
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
