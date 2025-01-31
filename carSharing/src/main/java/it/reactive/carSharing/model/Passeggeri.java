package it.reactive.carSharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Passeggeri extends Utenti{

    @ManyToMany(mappedBy = "passeggeri")
    private List<Viaggi> viaggi;
}
