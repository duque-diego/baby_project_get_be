package app.getfraldas.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by diegods on 11/08/18 d.C..
 */
@Entity
@Table(name = "tamanho")
public class Tamanho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

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

}
