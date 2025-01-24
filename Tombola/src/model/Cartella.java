package model;

import java.util.ArrayList;
import java.util.List;

public class Cartella {

    private List<Integer> numeri = new ArrayList<>();

    private String giocatore;

    private Integer id;

    public String getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(String giocatore) {
        this.giocatore = giocatore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getNumeri() {
        return numeri;
    }

    public void setNumeri(List<Integer> numeri) {
        this.numeri = numeri;
    }
}
