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

    Iterable<Promocao> findByTamanhoIdInAndModeloMarcaIdInAndAtivoTrueOrderByValorUnidadeAsc(
            @Param("tamanhos") HashSet<Long> tamanhos,
            @Param("marcas") HashSet<Long> marcas
    );

    Iterable<Promocao> findByValorUnidadeLessThanEqualAndTamanhoIdInAndModeloMarcaIdInAndAtivoTrueOrderByValorUnidadeAsc(
            @Param("maxValor") Double maxValor,
            @Param("tamanhos") HashSet<Long> tamanhos,
            @Param("marcas") HashSet<Long> marcas
    );

    Iterable<Promocao> findByTamanhoIdInAndAtivoTrueOrderByValorUnidadeAsc(
            @Param("tamanhos") HashSet<Long> tamanhos
    );

    Iterable<Promocao> findByValorUnidadeLessThanEqualAndTamanhoIdInAndAtivoTrueOrderByValorUnidadeAsc(
            @Param("maxValor") Double maxValor,
            @Param("tamanhos") HashSet<Long> tamanhos
    );

    Iterable<Promocao> findByModeloMarcaIdInAndAtivoTrueOrderByValorUnidadeAsc(
            @Param("marcas") HashSet<Long> marcas
    );

    Iterable<Promocao> findByValorUnidadeLessThanEqualAndModeloMarcaIdInAndAtivoTrueOrderByValorUnidadeAsc(
            @Param("maxValor") Double maxValor,
            @Param("marcas") HashSet<Long> marcas
    );

    Iterable<Promocao> findByAtivoTrue();

}
