package it.reactive.torneoDemo;

import it.reactive.torneoDemo.model.SquadraModel;
import it.reactive.torneoDemo.repository.dao.DaoGiocatori;
import it.reactive.torneoDemo.repository.dao.DaoSquadra;
import it.reactive.torneoDemo.repository.dao.DaoTifoseria;
import it.reactive.torneoDemo.repository.dao.DaoTorneo;
import it.reactive.torneoDemo.utility.DbProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(DbProfile.TORNEO_DAO_JDBC_STATEMENT)
class TorneoDemoApplicationTests {


	@Autowired
	DaoSquadra daoSquadra;

	@Test
	void testRecuperoNomeSquadraPerId() throws SQLException {
		SquadraModel squadraModel = daoSquadra.findById(1).get();
		assertEquals("Juventus", squadraModel.getNome());
	}

	@Test
	void testRecuperoIdPerId() throws SQLException {
		SquadraModel squadraModel = daoSquadra.findById(1).get();
		assertEquals(1, squadraModel.getIdSquadra());
	}


}
