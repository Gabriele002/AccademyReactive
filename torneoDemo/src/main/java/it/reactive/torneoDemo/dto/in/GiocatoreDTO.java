package it.reactive.torneoDemo.dto.in;


import javax.validation.constraints.NotBlank;

public class GiocatoreDTO {

    @NotBlank(message = "Il nome non puo essere null")
    private String nomeCognome;


    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome (String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }
}
