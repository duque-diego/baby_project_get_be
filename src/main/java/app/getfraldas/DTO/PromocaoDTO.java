package app.getfraldas.DTO;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

@Entity
public class PromocaoDTO implements Serializable {

    @Id
    private Long id;
    private FraldaDTO fralda;
    private LojaDTO loja;
    private TamanhoDTO tamanho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FraldaDTO getFralda() {
        return fralda;
    }

    public void setFralda(FraldaDTO fralda) {
        this.fralda = fralda;
    }

    public LojaDTO getLoja() {
        return loja;
    }

    public void setLoja(LojaDTO loja) {
        this.loja = loja;
    }

    public TamanhoDTO getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoDTO tamanho) {
        this.tamanho = tamanho;
    }
}
