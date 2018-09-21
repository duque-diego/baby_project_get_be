package app.getfraldas.repository;

import app.getfraldas.models.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;
import java.util.List;

/**
 * Created by fprado on 03/09/18
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByEmailEquals(@Param("email") String email);

    @Modifying
    @Query(value = "select DISTINCT u.id, u.email, u.nome, u.senha, u.telefone, u.cpf \n" +
            "from usuario u join usuario_loja ul join usuario_tamanho ut join usuario_marca um\n" +
            "where u.id = ul.usuario_id and u.id = ut.usuario_id and u.id = um.usuario_id\n" +
            "and ul.loja_id in :lojas and ut.tamanho_id in :tamanhos and um.marca_id in :marcas", nativeQuery = true)
    List<Usuario> findDistinctEmails(
            @Param("lojas") HashSet<Long> lojas,
            @Param("tamanhos") HashSet<Long> tamanhos,
            @Param("marcas") HashSet<Long> marcas
    );

}
