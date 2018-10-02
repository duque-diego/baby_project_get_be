package app.getfraldas.repository;

import app.getfraldas.models.Promocao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by fprado on 22/08/18
 */

public interface PromocaoRepository extends CrudRepository<Promocao, Long> {
    List<Promocao> findByLastUpdateGreaterThanEqual(@Param("minDate") Date minDate);

    Iterable<Promocao> findByTamanhoIdInAndModeloMarcaIdInOrderByValorUnidadeAsc(
            @Param("tamanhos") HashSet<Long> tamanhos,
            @Param("marcas") HashSet<Long> marcas
    );

    Iterable<Promocao> findByTamanhoIdInOrderByValorUnidadeAsc(
            @Param("tamanhos") HashSet<Long> tamanhos
    );

    Iterable<Promocao> findByModeloMarcaIdInOrderByValorUnidadeAsc(
            @Param("marcas") HashSet<Long> marcas
    );

}
