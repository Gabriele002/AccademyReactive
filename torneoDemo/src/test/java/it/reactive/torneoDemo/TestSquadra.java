package it.reactive.torneoDemo;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.utility.DbProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(DbProfile.TORNEO_DAO_JDBC_STATEMENT)
public class TestSquadra {

    @Autowired
    DaoSquadra daoSquadra;

    @Test
    void testRecuperoNomeSquadraPerId() throws SQLException {
        SquadraModel squadraModel = daoSquadra.findById(1).get();
        assertEquals("Juventus", squadraModel.getNome());
        assertEquals(1, squadraModel.getIdSquadra());
    }

    @Test
    void testRecuperoSquadraDadbPerNome(){
        SquadraModel squadraModel = daoSquadra.readForName("Juventus").get();
        assertEquals("Juventus", squadraModel.getNome());
        assertEquals("Bianco e nero", squadraModel.getColoriSociali());
        assertEquals(1, squadraModel.getIdSquadra());
    }


    @Test()
    void testLancioEccezioneSquadraGiaPresente() throws SQLException {
        SquadraDTO squadraDTO = new SquadraDTO();
        squadraDTO.setColoriSociali("oo");
        squadraDTO.setNome("Napoli");
        assertThrows(SquadraDuplicataException.class, () -> {
            daoSquadra.create(squadraDTO);
        });
    }


}
