package it.reactive.carSharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreaViaggioDto {

   @NotNull
   private LocalDateTime dataPartenza;
   @NotNull
   private LocalDateTime dataArrivo;

   @Min(1)
   @Max(7)
   private Integer numeroPassegeri;
   @NotBlank
   private String cittaPartenza;

   @NotBlank
   private String cittaArrivo;
}
