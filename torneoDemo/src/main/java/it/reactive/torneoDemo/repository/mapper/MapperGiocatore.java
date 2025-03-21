package it.reactive.torneoDemo.repository.mapper;
import it.reactive.torneoDemo.dto.resource.GiocatoreResponse;
import it.reactive.torneoDemo.model.GiocatoriModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class MapperGiocatore {

    public static GiocatoriModel rsToModel(ResultSet rs) throws SQLException {
        GiocatoriModel giocatoriModel = new GiocatoriModel();
        giocatoriModel.setNomeCognome(rs.getString("nome_cognome"));
        giocatoriModel.setNumeroAmmonizioni(rs.getInt("numero_ammonizioni"));
        giocatoriModel.setIdGiocatore(rs.getInt("id"));
        return giocatoriModel;
    }

    public static Set<GiocatoreResponse> modelToRs(Set<GiocatoriModel> giocatoriModel) {
        Set<GiocatoreResponse> giocatoreResponseHashSet = new HashSet<>();
        giocatoriModel.forEach(giocatore -> {
            GiocatoreResponse giocatoreResponse = new GiocatoreResponse();
            giocatoreResponse.setNomeCognome(giocatore.getNomeCognome());
            giocatoreResponse.setNumeroAmmonizioni(giocatore.getNumeroAmmonizioni());
            giocatoreResponse.setIdGiocatore(giocatore.getIdGiocatore());
            giocatoreResponseHashSet.add(giocatoreResponse);
        });
        return giocatoreResponseHashSet;
    }

    public static GiocatoreResponse modelToResponse(GiocatoriModel giocatoriModel){
        GiocatoreResponse giocatoreResponse = new GiocatoreResponse();
        giocatoreResponse.setIdGiocatore(giocatoriModel.getIdGiocatore());
        giocatoreResponse.setNomeCognome(giocatoriModel.getNomeCognome());
        giocatoreResponse.setNumeroAmmonizioni(giocatoriModel.getNumeroAmmonizioni());
        return giocatoreResponse;
    }

}
