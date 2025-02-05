package it.reactive.carSharing.service;

import it.reactive.carSharing.dto.SearchViaggiDto;
import it.reactive.carSharing.dto.enumDto.EnumOrdinamento;
import it.reactive.carSharing.dto.enumDto.EnumRatingAutista;
import it.reactive.carSharing.mapper.ViaggioMapper;
import it.reactive.carSharing.model.Viaggi;
import it.reactive.carSharing.response.ViaggioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViaggioService {


    @Autowired
    ViaggioMapper mapperViaggio;

    @Autowired
    EntityManager entityManager;

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");


    public List<ViaggioResponse> getViaggi(SearchViaggiDto searchViaggiDto,
                                           EnumOrdinamento enumOrdinamento,
                                           boolean prenotabile,
                                           EnumRatingAutista enumRatingAutista,
                                           String token) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Viaggi> criteriaQuery = criteriaBuilder.createQuery(Viaggi.class);
        Root<Viaggi> root = criteriaQuery.from(Viaggi.class);

        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(searchViaggiDto.getCittaArrivo())) {
            predicates.add(criteriaBuilder.like(root.get("cittaArrivo"), "%" + searchViaggiDto.getCittaArrivo() + "%"));
        }

        if (!ObjectUtils.isEmpty(searchViaggiDto.getCittaPartenza())) {
            predicates.add(criteriaBuilder.like(root.get("cittaPartenza"), "%" + searchViaggiDto.getCittaPartenza() + "%"));
        }

        if (searchViaggiDto.getDataPartenzaA() != null) {
            LocalDateTime dataPartenza = LocalDateTime.parse(searchViaggiDto.getDataPartenzaA(), formatter);
            predicates.add(criteriaBuilder.equal(root.get("dataArrivo"), searchViaggiDto.getDataPartenzaA()));
        }

        if (searchViaggiDto.getDataPartenzaDa() != null) {
            LocalDateTime dataPartenza = LocalDateTime.parse(searchViaggiDto.getDataPartenzaDa(), formatter);
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataPartenza"), searchViaggiDto.getDataPartenzaDa()));
        }

        criteriaBuilder.desc(root);
        criteriaQuery.orderBy();

        switch (enumOrdinamento) {
            case CRE_DATA:
                break;
            case CRE_RATING:
                break;
            case DES_DATA:
                break;
            case DES_RATING:
                break;
        }

        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        List<Viaggi> viaggi = entityManager.createQuery(criteriaQuery).getResultList();
        return viaggi
                .stream()
                .map(viaggio -> mapperViaggio.entityToResponse(viaggio))
                .collect(Collectors.toList());
    }
}
