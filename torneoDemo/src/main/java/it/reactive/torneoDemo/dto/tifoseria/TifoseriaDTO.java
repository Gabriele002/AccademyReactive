package it.reactive.torneoDemo.dto.tifoseria;

import javax.validation.constraints.NotBlank;

public class TifoseriaDTO {

    @NotBlank
    private String nomeTifoseria;

    public TifoseriaDTO() {
    }

    public String getNomeTifoseria() {
        return nomeTifoseria;
    }

    public void setNomeTifoseria(String nomeTifoseria) {
        this.nomeTifoseria = nomeTifoseria;
    }
}
