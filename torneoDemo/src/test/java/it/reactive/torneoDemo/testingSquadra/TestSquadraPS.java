package it.reactive.torneoDemo.testingSquadra;

import it.reactive.torneoDemo.utility.DaoProfile;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(DaoProfile.TORNEO_DAO_JDBC_PREPAREDSTATEMENT)
public class TestSquadraPS extends TestSquadra{
}
