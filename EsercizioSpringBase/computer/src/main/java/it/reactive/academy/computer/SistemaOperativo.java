package it.reactive.academy.computer;

import org.springframework.stereotype.Component;

public interface SistemaOperativo {
    String getNome();
    String getLinguaggio();

    default String getLineSeparator(){
        return System.lineSeparator();
    };
}

