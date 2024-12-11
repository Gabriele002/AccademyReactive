package it.reactive.torneoDemo.dto.squadra;

import it.reactive.torneoDemo.dto.giocatore.GiocatoreDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class SquadreDiGiocatoriDTO {

    @NotBlank
    @Size(min = 3, max = 20)
    private String nome;

    @NotBlank
    private String coloriSociali;

    @Valid
    private List<GiocatoreDTO> listaGiocatori;

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

    public @Valid List<GiocatoreDTO> getListaGiocatori() {
        return listaGiocatori;
    }

    public void setListaGiocatori(@Valid List<GiocatoreDTO> listaGiocatori) {
        this.listaGiocatori = listaGiocatori;
    }
}
