package it.reactive.torneoDemo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "torneo")
@NamedQuery(
        name = "findAllTornei",
        query = "select t from TorneoModel t"
)
public class TorneoModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTorneo;

    @Column(name = "nome_torneo")
    private String nomeTorneo;

    @ManyToMany
    @JoinTable(
            name = "squadra_torneo",
            joinColumns = @JoinColumn(name = "id_torneo"),
            inverseJoinColumns = @JoinColumn(name = "id_squadra")
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


    @Override
    public String toString() {
        return "TorneoModel{" +
                "idTorneo=" + idTorneo +
                ", nomeTorneo='" + nomeTorneo +
                '}';
    }
}
