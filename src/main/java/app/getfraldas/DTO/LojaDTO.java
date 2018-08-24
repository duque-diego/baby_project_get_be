package app.getfraldas.DTO;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

/**
 * Created by fprado on 22/08/18
 */
@Entity
public class LojaDTO implements Serializable {

    @Id
    private Long id;
    private String nome;
    private boolean isChecada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isChecada() {
        return isChecada;
    }

    public void setChecada(boolean checada) {
        isChecada = checada;
    }
}
