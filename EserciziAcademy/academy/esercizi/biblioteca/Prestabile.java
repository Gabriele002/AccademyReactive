package academy.esercizi.biblioteca;

public interface Prestabile {

    Libro prestare();

    Libro restituire();

    boolean verficaDisponibilita();

}
