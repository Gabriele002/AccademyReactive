package it.reactive.accademy.demoTorneoBatch.writerCustoms;

import it.reactive.accademy.demoTorneoBatch.config.ConfigurationDataSource;
import it.reactive.accademy.demoTorneoBatch.dto.SquadraDto;
import it.reactive.accademy.demoTorneoBatch.dto.TipoRecord;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static it.reactive.accademy.demoTorneoBatch.config.BatchConf.WRITER_SQUADRA;

@Configuration
public class WriterCustomsSquadra {

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
}
