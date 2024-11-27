package it.reactive.academy.computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Utility {

    public static Logger getLogger(Class<?> classe) {
        return LoggerFactory.getLogger(classe);
    }

    protected final Logger logger = getLogger(this.getClass());
}

