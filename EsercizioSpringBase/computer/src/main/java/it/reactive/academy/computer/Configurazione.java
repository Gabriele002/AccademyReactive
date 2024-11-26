package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurazione {

    @Value("${harddisk}")
    private String hardDisck;


    @Bean
    public HardDisck setHardDisck(){
        switch (hardDisck){
            case "HD":
                return new Hd();
            case "SSD":
                return new Ssd();
            default:
                throw new IllegalArgumentException("Errore: valore non valido per HARD_DISK: " + hardDisck);
        }
    }
}
