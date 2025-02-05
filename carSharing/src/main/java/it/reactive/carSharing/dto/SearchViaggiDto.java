package it.reactive.carSharing.dto;

import it.reactive.carSharing.dto.enumDto.EnumRatingAutista;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SearchViaggiDto {

    private String dataPartenzaDa;
    private String dataPartenzaA;
    private String cittaPartenza;
    private String cittaArrivo;
    private EnumRatingAutista ratingAutistaDa;
    private EnumRatingAutista ratingAutistaA;

}

