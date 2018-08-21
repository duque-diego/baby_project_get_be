package app.getfraldas.DTO;


import app.getfraldas.models.Preferencias;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.io.Serializable;

/**
 * Created by diegods on 28/05/18.
 */

@Entity
public class CadastroUsuarioAppDTO implements Serializable{


    private String nome;
    @Index
    private String email;
    private String telefone;
    @Id
    private String cpf;
    private String senha;
    private Preferencias preferencias;

    public CadastroUsuarioAppDTO(){

    }

    public CadastroUsuarioAppDTO(
            String nome,
            String email,
            String telefone,
            String cpf,
            String senha,
            boolean desejaReceberInfo,
            Preferencias preferencias){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
        this.preferencias = preferencias;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Preferencias getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(Preferencias preferencias) {
        this.preferencias = preferencias;
    }
}
