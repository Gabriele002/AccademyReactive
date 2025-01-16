package it.reactive.torneoDemo.testingSquadra;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.dto.in.SquadraDTO;
import it.reactive.torneoDemo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemo.dto.resource.SquadraResponse;
import it.reactive.torneoDemo.exception.SquadraDuplicataException;
import it.reactive.torneoDemo.exception.SquadraNonPresenteException;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.mapper.MapperSquadra;
import it.reactive.torneoDemo.service.SquadraService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    DaoGiocatori daoGiocatori;

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
    void testRecuperoSquadraDadbPerNome() {
        SquadraModel squadraModel = daoSquadra.readForName("Pompei").get();
        assertEquals("Pompei", squadraModel.getNome());
        assertEquals("Bianco e Nero", squadraModel.getColoriSociali());
        assertEquals(1, squadraModel.getIdSquadra());
    }

    @Test
    void testRecuperoNomeNull() {
        Optional<SquadraModel> squadraModelOptional = daoSquadra.readForName("Juventus");
        assertEquals(Optional.empty(), squadraModelOptional);
    }

    @Test
    void testCreateGiocatore() throws SQLException {
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
    void delete() throws Exception {
        List<SquadraModel> squadraModelsInit = daoSquadra.readAll(false);
       // daoSquadra.delete(4);
        squadraService.deleteSquadra(4);
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
        squadraModel3.setIdSquadra(5);
        squadraModel3.setNome("Napoli");
        squadraModel3.setColoriSociali("Azzurro e Bianco");
        squadraModelList.add(squadraModel);
        squadraModelList.add(squadraModel1);
        squadraModelList.add(squadraModel2);
        squadraModelList.add(squadraModel3);
        List<SquadraModel> squadraModels = daoSquadra.readAll(false);
        assertEquals(squadraModelList, squadraModels);
    }


    //Testing squadra service

    @Test
    void testDeleteSquadraNonPresente() {
        assertThrows(SquadraNonPresenteException.class, () -> {
            squadraService.deleteSquadra(90);
        });
    }

    @Test
    void deleteSquadraService() throws Exception {
        squadraService.deleteSquadra(5);
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
        //squadraService.returnSquadre(false);

        assertEquals(squadraModelList.size() -1, squadraModels.size()); // - 1 perche richiamo un altra delete prima di questa
    }

    @Test()
    void testLancioEccezioneSquadraGiaPresente() throws Exception {
        SquadraDTO squadraDTO = new SquadraDTO();
        squadraDTO.setColoriSociali("oo");
        squadraDTO.setNome("Pompei");
        assertThrows(SquadraDuplicataException.class, () -> {
            squadraService.createSquadra(squadraDTO);
        });
    }

    @Test
    void testCreateService() throws Exception {
        SquadraDTO squadraDTO = new SquadraDTO();
        squadraDTO.setColoriSociali("Nero");
        squadraDTO.setNome("Pippo");
        SquadraResponse squadraResponse = squadraService.createSquadra(squadraDTO);
        List<SquadraResponse> squadraModels = squadraService.returnSquadre(false);
        SquadraModel squadraModel = daoSquadra.readForName("Pippo").get();
        SquadraResponse squadraResponseMapper = MapperSquadra.modelToResponse(squadraModel);
        assertEquals(squadraResponseMapper, squadraResponse);
    }

    @Test
    void testRitornoSquadraConGiocatori() throws SQLException {
        List<SquadraResponse> squadraResponses = squadraService.returnSquadre(true);
        SquadraResponse squadraResponse = new SquadraResponse();
        Set<GiocatoreResponse> giocatoriResponse = new HashSet<>();
        GiocatoreResponse giocatori = new GiocatoreResponse();
        giocatori.setNumeroAmmonizioni(0);
        giocatori.setNomeCognome("Cristiano Ronaldo");
        giocatori.setIdGiocatore(1);
        giocatoriResponse.add(giocatori);
        squadraResponse.setGiocatori(giocatoriResponse);
        assertEquals(giocatoriResponse, squadraResponses.get(0).getGiocatori());
    }

    @Test
    void testSquadraSenzaGiocatori() throws SQLException {
        List<SquadraResponse> squadraResponseList = squadraService.returnSquadre(true);
        Set<GiocatoreResponse> giocatoreResponseSet = squadraResponseList.get(1).getGiocatori();
        assertEquals(new HashSet<>(), giocatoreResponseSet);
    }

    @Test
    void testRecuperoSquadraSenzaGiocatoriService() throws SQLException {
        List<SquadraResponse> squadraResponseList = squadraService.returnSquadre(false);
        SquadraResponse squadraResponse = new SquadraResponse();
        Set<GiocatoreResponse> giocatoriModels = new HashSet<>();
        squadraResponse.setGiocatori(giocatoriModels);
        assertEquals(giocatoriModels, squadraResponseList.get(0).getGiocatori());
    }

    @Test
    void testAggiungiGiocatoreService() throws Exception {
        GiocatoreDTO giocatoreDTO = new GiocatoreDTO();
        giocatoreDTO.setNomeCognome("Pippo456");
        squadraService.aggiungiGiocatore(giocatoreDTO, 5);
        SquadraModel squadraModel = daoSquadra.findById(5).get();
        Set<GiocatoriModel> giocatoriModelsRecuperati = daoGiocatori.readGiocatoriWithIdSquadra(1);
        Set<GiocatoriModel> giocatoriModels = new HashSet<>();
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        giocatoriModel.setSquadra(squadraModel);
        giocatoriModel.setIdGiocatore(2);
        giocatoriModel.setNomeCognome("Pippo456");
        giocatoriModel.setNumeroAmmonizioni(0);
        giocatoriModels.add(giocatoriModel);
        assertEquals(giocatoriModels.size(), giocatoriModelsRecuperati.size());
    }

}
