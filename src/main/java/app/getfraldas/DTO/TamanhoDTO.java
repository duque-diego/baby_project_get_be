package app.getfraldas.DTO;

import app.getfraldas.models.Promocao;
import app.getfraldas.models.Tamanho;

import java.io.Serializable;

/**
 * Created by fprado on 22/08/18
 */

public class TamanhoDTO implements Serializable {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static TamanhoDTO toTamanhoDTO(Tamanho tamanho){

        TamanhoDTO tamanhoDTO = new TamanhoDTO();
        if(tamanho != null){
            tamanhoDTO.setNome(tamanho.getNome());
        }
        return tamanhoDTO;

    }

}
