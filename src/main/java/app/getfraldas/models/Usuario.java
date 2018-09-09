package app.getfraldas.models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by fprado on 08/09/18
 */

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "telefone")
    private String telefone;

    @ManyToMany
    @JoinTable(name = "usuario_loja",
            joinColumns = @JoinColumn(name = "usuarioId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "lojaId", referencedColumnName = "id"))
    private Set<Loja> lojas;

    @ManyToMany
    @JoinTable(name = "usuario_marca",
            joinColumns = @JoinColumn(name = "usuarioId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "marcaId", referencedColumnName = "id"))
    private Set<Marca> marcas;

    @ManyToMany
    @JoinTable(name = "usuario_tamanho",
            joinColumns = @JoinColumn(name = "usuarioId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tamanhoId", referencedColumnName = "id"))
    private Set<Tamanho> tamanhos;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(Set<Loja> lojas) {
        this.lojas = lojas;
    }

    public Set<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(Set<Marca> marcas) {
        this.marcas = marcas;
    }

    public Set<Tamanho> getTamanhos() {
        return tamanhos;
    }

    public void setTamanhos(Set<Tamanho> tamanhos) {
        this.tamanhos = tamanhos;
    }
}
