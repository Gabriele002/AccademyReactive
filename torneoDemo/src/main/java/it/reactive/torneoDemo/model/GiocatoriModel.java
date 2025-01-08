package it.reactive.torneoDemo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "giocatore")
public class GiocatoriModel {
    @Id
    @Column(name = "id")
    private Integer idGiocatore;
    private String nomeCognome;
    private Integer numeroAmmonizioni;
    @ManyToOne
    @JoinColumn(name = "id_squadra")
    private SquadraModel squadra;

    public Integer getIdGiocatore() {
        return idGiocatore;
    }

    public void setIdGiocatore(Integer idGiocatore) {
        this.idGiocatore = idGiocatore;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public Integer getNumeroAmmonizioni() {
        return numeroAmmonizioni;
    }

    public void setNumeroAmmonizioni(Integer numeroAmmonizioni) {
        this.numeroAmmonizioni = numeroAmmonizioni;
    }

    public SquadraModel getSquadra() {
        return squadra;
    }

    public void setSquadra(SquadraModel squadra) {
        this.squadra = squadra;
    }

    @Override
    public String toString() {
        return "GiocatoriModel{" +
                "idGiocatore=" + idGiocatore +
                ", nomeCognome='" + nomeCognome + '\'' +
                ", numeroAmmonizioni=" + numeroAmmonizioni +
                ", squadra=" + squadra +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiocatoriModel that = (GiocatoriModel) o;
        return Objects.equals(idGiocatore, that.idGiocatore) && Objects.equals(nomeCognome, that.nomeCognome) && Objects.equals(numeroAmmonizioni, that.numeroAmmonizioni) && Objects.equals(squadra, that.squadra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGiocatore, nomeCognome, numeroAmmonizioni, squadra);
    }
}
