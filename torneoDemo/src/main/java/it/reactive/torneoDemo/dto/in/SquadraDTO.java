package it.reactive.torneoDemo.dto.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SquadraDTO {

    @NotBlank
    private String coloriSociali;

    @NotBlank
    @Size(min = 3, max = 20, message = "Il nome della squadra deve essere almeno di 3 caratterie massimo di 20")
    private String nome;

    public SquadraDTO() {
    }

    public @NotBlank String getColoriSociali() {
        return coloriSociali;
    }

    public void setColoriSociali(@NotBlank String coloriSociali) {
        this.coloriSociali = coloriSociali;
    }

    public @NotBlank @Size(min = 3, message = "Il nome della squadra deve essere almeno di 3 caratterie massimo di 20") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @Size(min = 3, message = "Il nome della squadra deve essere almeno di 3 caratterie massimo di 20") String nome) {
        this.nome = nome;
    }
}
