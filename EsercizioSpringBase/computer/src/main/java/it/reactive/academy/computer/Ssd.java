package it.reactive.academy.computer;

import org.springframework.stereotype.Component;

public class Ssd implements HardDisck{

    @Override
    public String tipo() {
        return "Ssd";
    }
}
