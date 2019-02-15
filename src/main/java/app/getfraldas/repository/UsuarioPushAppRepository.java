package app.getfraldas.repository;

import app.getfraldas.DAO.ObjectifyGenericDAO;
import app.getfraldas.DTO.CadastroUsuarioAppDTO;
import app.getfraldas.DTO.UsuarioPushAppControleDTO;
import app.getfraldas.models.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioPushAppRepository extends ObjectifyGenericDAO<UsuarioPushAppControleDTO> {
    public CadastroUsuarioAppDTO getById(Long id){
        ofy().clear();
        return ofy().transactionless().load().type(CadastroUsuarioAppDTO.class).id(id).now();
    }
}

