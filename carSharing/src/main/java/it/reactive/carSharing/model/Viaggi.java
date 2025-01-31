package it.reactive.carSharing.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Viaggi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idViaggio;

    private LocalDateTime dataPartenza;

    private LocalDateTime dataArrivo;

    private Integer numeroPassegeri;

    private String cittaPartenza;

    private String cittaArrivo;

    @ManyToOne
    @JoinColumn(name = "autista_id")
    private Autisti autista;


    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "viaggi_idViaggio"))
    private List<Passeggeri> passeggeri = new ArrayList<>();

}
