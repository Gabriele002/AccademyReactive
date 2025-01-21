package it.reactive.accademy.demoTorneoBatch.ClassifierComposite;

import it.reactive.accademy.demoTorneoBatch.dto.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.ClassifierCompositeItemProcessor;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static it.reactive.accademy.demoTorneoBatch.config.BatchConf.CLASSIFAIER;

@Configuration
public class ClassifaierTorneoDemoBatch {

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
