package app.getfraldas.DTO;

import java.io.Serializable;

/**
 * Created by fprado on 22/08/18
 */
public class LojaDTO implements Serializable {

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
}
