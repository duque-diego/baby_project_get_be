package app.getfraldas.DTO;

import app.getfraldas.models.Modelo;

import java.io.Serializable;

/**
 * Created by fprado on 22/08/18
 */

public class FraldaDTO implements Serializable {

    private String nome;
    private String imageLink;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public static FraldaDTO toFraldaDTO (Modelo modelo){
        FraldaDTO fraldaDTO = new FraldaDTO();
        if(modelo != null){
            fraldaDTO.setNome(modelo.getNome());
            fraldaDTO.setImageLink(modelo.getImageLink());
        }
        return fraldaDTO;
    }
}
