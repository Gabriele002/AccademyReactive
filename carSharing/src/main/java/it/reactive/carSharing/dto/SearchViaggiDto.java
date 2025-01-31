package it.reactive.carSharing.dto;

import it.reactive.carSharing.dto.enumDto.EnumRatingAutista;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchViaggiDto {


    private LocalDateTime dataPartenzaDa;
    private LocalDateTime dataPartenzaA;
    private String cittaPartenza;
    private String cittaArrivo;
    private EnumRatingAutista ratingAutistaDa;
    private EnumRatingAutista ratingAutistaA;

}

