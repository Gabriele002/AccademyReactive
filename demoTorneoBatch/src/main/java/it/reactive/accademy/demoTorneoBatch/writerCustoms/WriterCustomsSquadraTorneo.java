package it.reactive.accademy.demoTorneoBatch.writerCustoms;

import it.reactive.accademy.demoTorneoBatch.config.ConfigurationDataSource;
import it.reactive.accademy.demoTorneoBatch.dto.SquadraTorneoDto;
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

import static it.reactive.accademy.demoTorneoBatch.config.BatchConf.WRITER_SQUADRA_TORNEO;

@Configuration
public class WriterCustomsSquadraTorneo {

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
}
