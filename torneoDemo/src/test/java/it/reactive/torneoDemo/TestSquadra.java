package it.reactive.torneoDemo;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.service.SquadraService;
import it.reactive.torneoDemo.utility.DbProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(DbProfile.TORNEO_DAO_SPRING_JDBC_QUERY_PSC)
public class TestSquadra {

    @Autowired
    DaoSquadra daoSquadra;

    @Autowired
    SquadraService squadraService;

    @Test
    void testRecuperoNomeSquadraPerId() throws SQLException {
        SquadraModel squadraModel = daoSquadra.findById(1).get();
        assertEquals("Pompei", squadraModel.getNome());
        assertEquals("Bianco e Nero", squadraModel.getColoriSociali());
        assertEquals(1, squadraModel.getIdSquadra());
    }

    @Test
    void testRecuperoNomeSquadraPerIdNull() throws SQLException {
        Optional<SquadraModel> squadraModel = daoSquadra.findById(8);
        assertEquals(Optional.empty(), squadraModel);
    }

    @Test
    void testRecuperoSquadraDadbPerNome(){
        SquadraModel squadraModel = daoSquadra.readForName("Pompei").get();
        assertEquals("Pompei", squadraModel.getNome());
        assertEquals("Bianco e Nero", squadraModel.getColoriSociali());
        assertEquals(1, squadraModel.getIdSquadra());
    }

    @Test
    void testRecuperoNomeNull(){
        Optional<SquadraModel> squadraModelOptional = daoSquadra.readForName("Juventus");
        assertEquals(Optional.empty(), squadraModelOptional);
    }

    @Test()
    void testLancioEccezioneSquadraGiaPresente() throws Exception {
        SquadraDTO squadraDTO = new SquadraDTO();
        squadraDTO.setColoriSociali("oo");
        squadraDTO.setNome("Napoli");
        assertThrows(SquadraDuplicataException.class, () -> {
            squadraService.createSquadra(squadraDTO);
        });
    }

    @Test
    void testCreate() throws SQLException {
        SquadraDTO squadraDTO = new SquadraDTO();
        squadraDTO.setNome("Sassuolo");
        squadraDTO.setColoriSociali("Giallo");
        SquadraModel squadraModel = daoSquadra.create(squadraDTO);

        SquadraModel squadraModelCompare = new SquadraModel();
        squadraModelCompare.setIdSquadra(6);
        squadraModelCompare.setNome("Sassuolo");
        squadraModelCompare.setColoriSociali("Giallo");
        SquadraModel squadraModelFind = daoSquadra.findById(6).get();
        assertEquals(squadraModelCompare, squadraModelFind);
        assertEquals(new HashSet<GiocatoriModel>(), squadraModel.getGiocatori());
    }

    @Test
    void testRecuperoGiocatoriDaSquadraTrue() throws SQLException {
        boolean giocatori = true;
        List<SquadraModel> squadraModels = daoSquadra.readAll(giocatori);
        SquadraModel squadraModel = new SquadraModel();
        Set<GiocatoriModel> giocatoriModels = new HashSet<>();
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        giocatoriModel.setNumeroAmmonizioni(0);
        giocatoriModel.setNomeCognome("Cristiano Ronaldo");
        giocatoriModel.setIdGiocatore(1);
        giocatoriModels.add(giocatoriModel);
        squadraModel.setGiocatori(giocatoriModels);
        assertEquals(giocatoriModels, squadraModels.get(0).getGiocatori());
    }

    @Test
    void testRecuperoGiocatoriDaSquadraFalse() throws SQLException {
        boolean giocatori = false;
        List<SquadraModel> squadraModels = daoSquadra.readAll(giocatori);
        assertEquals(new HashSet<GiocatoriModel>(), squadraModels.get(0).getGiocatori());
    }

}
