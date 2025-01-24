package it.reactive.torneoDemoMongo.dto.in;

import it.reactive.torneoDemoMongo.model.GiocatoriModelMongo;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;

public class SquadreDiGiocatoriDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String nome;

    @NotBlank
    private String coloriSociali;

    @Valid
    private HashSet<GiocatoriModelMongo> listaGiocatori;

    public @NotBlank @Size(min = 3, max = 20) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 3, max = 20) String nome) {
        this.nome = nome;
    }

    public @NotBlank String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(@NotBlank String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    public @Valid HashSet<GiocatoriModelMongo> getListaGiocatori() {
        return listaGiocatori;
    }

    public void setListaGiocatori(@Valid HashSet<GiocatoriModelMongo> listaGiocatori) {
        this.listaGiocatori = listaGiocatori;
    }
}
