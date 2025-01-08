package it.reactive.torneoDemo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "torneo")
public class TorneoModel {

    @Id
    private Integer idTorneo;
    private String nomeTorneo;
    @ManyToMany
    @JoinTable(
            name = "squadra_torneo",
            joinColumns = @JoinColumn(name = "id_squadra"),
            inverseJoinColumns = @JoinColumn(name = "id_torneo")
    )
    private Set<SquadraModel> squadre = new HashSet<>();

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(Integer idTorneo) {
        this.idTorneo = idTorneo;
    }

    public String getNomeTorneo() {
        return nomeTorneo;
    }

    public void setNomeTorneo(String nomeTorneo) {
        this.nomeTorneo = nomeTorneo;
    }

    public Set<SquadraModel> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<SquadraModel> squadre) {
        this.squadre = squadre;
    }
}
