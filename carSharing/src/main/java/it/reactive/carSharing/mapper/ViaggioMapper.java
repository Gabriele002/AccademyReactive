package it.reactive.carSharing.mapper;
import it.reactive.carSharing.dto.CreaViaggioDto;
import it.reactive.carSharing.model.Viaggi;
import it.reactive.carSharing.response.ViaggioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ViaggioMapper {
    ViaggioMapper INSTANCE = Mappers.getMapper(ViaggioMapper.class);

    Viaggi dtoToEntity(CreaViaggioDto creaViaggioDto);

    CreaViaggioDto entityToDto(Viaggi viaggio);

    ViaggioResponse entityToResponse(Viaggi viaggio);

}
