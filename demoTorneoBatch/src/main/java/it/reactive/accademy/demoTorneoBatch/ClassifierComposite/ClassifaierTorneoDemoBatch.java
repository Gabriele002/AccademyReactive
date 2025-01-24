package it.reactive.accademy.demoTorneoBatch.ClassifierComposite;

import it.reactive.accademy.demoTorneoBatch.dto.*;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static it.reactive.accademy.demoTorneoBatch.config.BatchConf.CLASSIFAIER;

@Configuration
public class ClassifaierTorneoDemoBatch {

    @Bean(CLASSIFAIER)
    public ClassifierCompositeItemProcessor<List<String>, TipoRecord> classifierCompositeItemProcessor(@Qualifier("JDBC_TEMPLATE") JdbcTemplate jdbcTemplate) {
        Classifier<List<String>, ItemProcessor<?, ? extends TipoRecord>> classifier = new Classifier<>() {
            @Override
            public ItemProcessor<?, ? extends TipoRecord> classify(List<String> listaAttributi) {
                String tipo = listaAttributi.get(0);
                return switch (tipo) {
                    case "TO" -> new ItemProcessor<List<String>, TipoRecord>() {
                        @Override
                        public TipoRecord process(List<String> item) throws Exception {
                            TorneoDto torneoDto = new TorneoDto();
                            String sql = "select nextval(pg_get_serial_sequence('torneo', 'id'))";
                            Integer idTorneo = jdbcTemplate.queryForObject(sql, Integer.class);
                            torneoDto.setNome(listaAttributi.get(1));
                            torneoDto.setId(idTorneo);
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            stepContext.put(torneoDto.getNome() + "torneo", torneoDto.getId());
                            return torneoDto;
                        }
                    };
                    case "SQ" -> new ItemProcessor<List<String>, TipoRecord>() {
                        @Override
                        public TipoRecord process(List<String> item) throws Exception {
                            String sql = "select nextval(pg_get_serial_sequence('squadra', 'id'))";
                            Integer idSquadra = jdbcTemplate.queryForObject(sql, Integer.class);
                            SquadraDto squadraDto = new SquadraDto();
                            squadraDto.setNome(listaAttributi.get(1));
                            squadraDto.setColori_sociali(listaAttributi.get(2));
                            squadraDto.setTifoseriaNome(listaAttributi.get(3));
                            squadraDto.setId(idSquadra);
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            stepContext.put(squadraDto.getNome() + "squadra", squadraDto.getId());
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
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            Integer idSquadra = (Integer) stepContext.get(giocatoreDto.getNomeSquadra() + "squadra");
                            giocatoreDto.setIdSquadra(idSquadra);
                            return giocatoreDto;
                        }
                    };
                    case "TS" -> new ItemProcessor<List<String>, TipoRecord>() {
                        @Override
                        public TipoRecord process(List<String> item) throws Exception {
                            SquadraTorneoDto squadraTorneoDto = new SquadraTorneoDto();
                            squadraTorneoDto.setNomeSquadra(listaAttributi.get(2));
                            squadraTorneoDto.setNomeTorneo(listaAttributi.get(1));
                            StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
                            ExecutionContext stepContext = stepExecution.getExecutionContext();
                            Integer idSquadra = (Integer) stepContext.get(squadraTorneoDto.getNomeSquadra() + "squadra");
                            Integer idTorneo = (Integer) stepContext.get(squadraTorneoDto.getNomeTorneo() + "torneo");
                            squadraTorneoDto.setIdTorneo(idTorneo);
                            squadraTorneoDto.setIdSquadra(idSquadra);
                            return squadraTorneoDto;
                        }
                    };
                    default -> new ItemProcessor<String, TipoRecord>() {
                        @Override
                        public TipoRecord process(String riga) throws Exception {
                            throw new RuntimeException("Tipo non gestito");
                        }
                    };
                };
            }
        };
        ClassifierCompositeItemProcessor<List<String>, TipoRecord> classifierProcessor = new ClassifierCompositeItemProcessor<>();
        classifierProcessor.setClassifier(classifier);
        return classifierProcessor;
    }
}
