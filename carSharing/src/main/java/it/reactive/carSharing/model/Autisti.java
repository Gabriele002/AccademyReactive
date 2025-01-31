package it.reactive.carSharing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Autisti extends Utenti{

    private String targa;
}
