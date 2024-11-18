package academy.esercizi.biblioteca;

import java.time.LocalDate;

public class LibroPrestato extends Libro{

    private LocalDate dataPrestito;
    private LocalDate finePrestito;
    private final int durataPrestito = 30;


    LibroPrestato(String autore, String titolo,int id, LocalDate dataPrestito) {
        super(autore, titolo,id);
        this.dataPrestito = dataPrestito;
        this.finePrestito = calcolaDataFinePrestito();
    }


    public LocalDate getDataPrestito() {
        return dataPrestito;
    }

    public void setDataPrestito(LocalDate dataPrestito) {
        this.dataPrestito = dataPrestito;
    }

    public LocalDate calcolaDataFinePrestito(){
        return dataPrestito.plusDays(durataPrestito);
    }

    public boolean isOverDue(){
        return dataPrestito.isAfter(finePrestito);
    }
    @Override
    public String toString() {
        return super.toString()+
                " LibroPrestato " +
                "dataPrestito=" + dataPrestito +
                ", finePrestito=" + finePrestito +
                ", durataPrestito=" + durataPrestito ;
    }
}
