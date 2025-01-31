package it.reactive.carSharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Valutazioni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggi viaggio;

    @ManyToOne
    @JoinColumn(name = "passeggero_id")
    private Passeggeri passeggero;

    private Integer rating;
    private String valutazioneTestuale;
}
