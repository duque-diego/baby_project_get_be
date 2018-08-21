package app.getfraldas.repository;

import app.getfraldas.DAO.ObjectifyGenericDAO;
import app.getfraldas.DTO.CadastroUsuarioAppDTO;
import org.springframework.stereotype.Repository;

@Repository
public class CadastroUsuarioAppRepository extends ObjectifyGenericDAO<CadastroUsuarioAppDTO> {
    public CadastroUsuarioAppDTO getById(Long id){
        ofy().clear();
        return ofy().transactionless().load().type(CadastroUsuarioAppDTO.class).id(id).now();
    }
}

