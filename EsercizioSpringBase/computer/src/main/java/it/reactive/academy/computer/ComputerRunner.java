package it.reactive.academy.computer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class ComputerRunner extends Utility{

        private HardDisck hardDisck;
        private Schermo schermo;
        private Tastiera tastiera;

    public ComputerRunner(HardDisck hardDisck, Schermo schermo) {
        this.hardDisck = hardDisck;
        this.schermo = schermo;
    }

    @Autowired (required = false)
    public void setTastiera(Tastiera tastiera){
        this.tastiera = tastiera;
    }

    @Autowired
    @Qualifier("windows")
    private SistemaOperativo sistemaOperativo;


    public void saluta() {
        logger.info("Sono il computer");
        logger.info("uso lo schermo: {}", schermo.getSchermo());
        logger.info("sono configurato con il sistema operativo: {}",  sistemaOperativo.getNome());
        logger.info("ed il linguaggio: {}", sistemaOperativo.getLinguaggio());
        logger.info("Il separatore di linee: {}", sistemaOperativo.getLineSeparator());
        if (tastiera!=null) {
            logger.info("uso la tastiera {} ", tastiera.tasti());
        } else {
            logger.info("non hai configurato la tastiera");
        }
        logger.info("l'hard disk scelto e: {}", hardDisck.tipo() );
    }

}
