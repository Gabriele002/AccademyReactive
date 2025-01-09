package it.reactive.torneoDemo.testingSquadra;

import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.service.SquadraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public abstract class TestSquadra {

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

    @Test
    void delete () throws SQLException {
        daoSquadra.delete(5);
        List<SquadraModel> squadraModelList = new ArrayList<>();
        SquadraModel squadraModel = new SquadraModel();
        squadraModel.setIdSquadra(1);
        squadraModel.setNome("Pompei");
        squadraModel.setColoriSociali("Bianco e Nero");
        SquadraModel squadraModel1 = new SquadraModel();
        squadraModel1.setIdSquadra(2);
        squadraModel1.setNome("Inter");
        squadraModel1.setColoriSociali("Nero e Azzurro");
        SquadraModel squadraModel2 = new SquadraModel();
        squadraModel2.setIdSquadra(3);
        squadraModel2.setNome("Milan");
        squadraModel2.setColoriSociali("Rosso e Nero");
        SquadraModel squadraModel3 = new SquadraModel();
        squadraModel3.setIdSquadra(4);
        squadraModel3.setNome("Roma");
        squadraModel3.setColoriSociali("Giallo e Rosso");
        squadraModelList.add(squadraModel);
        squadraModelList.add(squadraModel1);
        squadraModelList.add(squadraModel2);
        squadraModelList.add(squadraModel3);
        List<SquadraModel> squadraModels = daoSquadra.readAll(false);
        squadraModels.forEach(System.out::println);
        System.out.println("_________________");
        squadraModelList.forEach(System.out::println);
        assertEquals(squadraModelList, squadraModels);
    }
}
