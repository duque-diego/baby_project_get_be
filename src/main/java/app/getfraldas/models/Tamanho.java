package app.getfraldas.models;

/**
 * Created by diegods on 11/08/18 d.C..
 */
public class Tamanho {

    private long id;
    private String nome;
    private boolean isChecada;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
