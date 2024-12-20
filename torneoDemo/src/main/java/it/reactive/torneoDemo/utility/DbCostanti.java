package it.reactive.torneoDemo.utility;

import org.springframework.stereotype.Component;

@Component
public class DbCostanti {

    public final String SQUADRA_TABLE = "squadra";
    public final String TIFOSERIA_TABLE = "tifoseria";
    public final String GIOCATORE_TABLE = "giocatore";
    public final String TORNEO_TABLE = "torneo";
    public final String SQUADRA_TORNEO_TABLE = "squadra_torneo";

    public final String SQUADRA_ID_COL = "id";
    public final String SQUADRA_NOME_COL = "nome";
    public final String SQUADRA_COLORI_SOCIALI_COL = "colori_sociali";

    public final String TIFOSERIA_ID_COL = "id";
    public final String TIFOSERIA_NOME_TIFOSERIA_COL = "nome_tifoseria";
    public final String TIFOSERIA_ID_SQUADRA_COL = "id_squadra";

    public final String GIOCATORE_ID_COL = "id";
    public final String GIOCATORE_NOME_COGNOME_COL = "nome_cognome";
    public final String GIOCATORE_NUMERO_AMMONIZIONI_COL = "numero_ammonizioni";
    public final String GIOCATORE_ID_SQUADRA_COL = "id_squadra";

    public final String TORNEO_ID_COL = "id";
    public final String TORNEO_NOME_TORNEO_COL = "nome_torneo";

    public final String SQUADRA_TORNEO_ID_SQUADRA_COL = "id_squadra";
    public final String SQUADRA_TORNEO_ID_TORNEO_COL = "id_torneo";

}
