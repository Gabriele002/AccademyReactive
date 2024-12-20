package it.reactive.torneoDemo.repository.dao;

import it.reactive.torneoDemo.dto.in.GiocatoreDTO;
import it.reactive.torneoDemo.dto.in.TorneoDTO;
import it.reactive.torneoDemo.model.GiocatoriModel;
import it.reactive.torneoDemo.model.TorneoModel;

public interface DaoTorneo {
    TorneoModel create(TorneoDTO torneoDTO);
    TorneoModel read(int id);
    TorneoModel update(int id, TorneoDTO torneoDTO);
    TorneoModel delete(int id);
}
