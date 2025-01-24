package it.reactive.accademy.demoTorneoBatch.writerCustoms;

import it.reactive.accademy.demoTorneoBatch.dto.SquadraDto;
import it.reactive.accademy.demoTorneoBatch.dto.TipoRecord;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import static it.reactive.accademy.demoTorneoBatch.config.ConfigurationDataSource.TORNEO_DATASOURCE;

@Component(WriterCustomInsertSquadraTifoseria.WRITER_SQUADRA_TIFOSERIA)
public class WriterCustomInsertSquadraTifoseria implements ItemStreamWriter<TipoRecord> {

    public static final String WRITER_SQUADRA_TIFOSERIA = "WRITER_SQUADRA_TIFOSERIA";
    private final JdbcBatchItemWriter<TipoRecord> squadra;
    private final JdbcBatchItemWriter<TipoRecord> tifoseria;

    public WriterCustomInsertSquadraTifoseria(@Qualifier(TORNEO_DATASOURCE)DataSource dataSource) {
        squadra = new JdbcBatchItemWriterBuilder<TipoRecord>()
                .dataSource(dataSource)
                .sql("insert into squadra(id,nome,colori_sociali) values(?,?,?)")
                .itemPreparedStatementSetter((item, ps) -> {
                    SquadraDto squadraDto = (SquadraDto) item;
                    ps.setInt(1, squadraDto.getId());
                    ps.setString(2, squadraDto.getNome());
                    ps.setString(3, squadraDto.getColori_sociali());
                })
                .build();
        tifoseria = new JdbcBatchItemWriterBuilder<TipoRecord>()
                .dataSource(dataSource)
                .sql("insert into tifoseria(nome_tifoseria, id_squadra) values(?,?)")
                .itemPreparedStatementSetter((item, ps) -> {
                    SquadraDto squadraDto = (SquadraDto) item;
                    ps.setString(1, squadraDto.getTifoseriaNome());
                    ps.setInt(2, squadraDto.getId());
                })
                .build();
    }

    @Override
    public void write(Chunk<? extends TipoRecord> chunk) throws Exception {
        for (TipoRecord tipoRecord : chunk) {
            squadra.write(Chunk.of(tipoRecord));
            SquadraDto squadraDto = (SquadraDto) tipoRecord;
            if (!squadraDto.getTifoseriaNome().equals("-")) {
                tifoseria.write(Chunk.of(tipoRecord));
            }
        }
    }
}
