package it.reactive.torneoDemo.testingSquadra;

import it.reactive.torneoDemo.utility.DaoProfile;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(DaoProfile.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class TestingSquadraJpaRepo extends TestSquadra{
}
