package it.reactive.accademy.demoTorneoBatch.writerCustoms;

import it.reactive.accademy.demoTorneoBatch.config.ConfigurationDataSource;
import it.reactive.accademy.demoTorneoBatch.dto.GiocatoreDto;
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

import static it.reactive.accademy.demoTorneoBatch.config.BatchConf.WRITER_GIOCATORI;

@Configuration
public class WriterCustomsGiocatori {

    @Bean(WRITER_GIOCATORI)
    public ItemStreamWriter<TipoRecord> writerGiocatori(@Qualifier(ConfigurationDataSource.TORNEO_DATASOURCE) DataSource dataSource) {
        return new ItemStreamWriter<TipoRecord>() {
            @Override
            public void write(Chunk<? extends TipoRecord> chunk) throws Exception {
                Connection con = null;
                try {
                    con = dataSource.getConnection();

                PreparedStatement pr = con.prepareStatement("select s.id from squadra s where nome = ?");
                PreparedStatement prInsert = con.prepareStatement("insert into giocatore(nome_cognome, numero_ammonizioni, id_squadra) values (?,?,?)");

                    chunk.forEach(tipoFile -> {
                    GiocatoreDto giocatoreDto = (GiocatoreDto) tipoFile;
                    try {
                        ResultSet rs = null;
                        pr.setString(1, giocatoreDto.getNomeSquadra());
                        rs = pr.executeQuery();
                        int id = 0;
                        if (rs.next()) {
                            id = rs.getInt("id");
                        }
                        prInsert.setString(1, giocatoreDto.getNomeCognome());
                        prInsert.setInt(2, 0);
                        prInsert.setInt(3, id);
                        prInsert.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    con.close();
                }
            }
        };
    }
}
