package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Schermo {

    @Value("${DIM_SCHERMO}")
    String dimensioneSchermo;

    public String getSchermo() {
        return dimensioneSchermo;
    }
}
