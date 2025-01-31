package it.reactive.carSharing.dto.enumDto;

import lombok.Getter;

@Getter
public enum EnumRatingAutista {
        UNO(1),
        DUE(2),
        TRE(3),
        QUATTRO(4),
        CINQUE(5);

        private final int valore;

        EnumRatingAutista(int valore) {
            this.valore = valore;
        }

}
