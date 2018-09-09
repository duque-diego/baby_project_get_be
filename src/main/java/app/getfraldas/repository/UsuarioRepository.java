package app.getfraldas.repository;

import app.getfraldas.models.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fprado on 03/09/18
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
