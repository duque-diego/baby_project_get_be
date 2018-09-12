package app.getfraldas.DTO;

import app.getfraldas.models.Loja;
import app.getfraldas.models.Modelo;
import app.getfraldas.models.Tamanho;


/**
 * Created by diegods on 11/09/18 d.C..
 */
public class DadosPromocaoDTO {

    private Iterable<Loja> lojas;
    private Iterable<Tamanho> tamanhos;
    private Iterable<Modelo> modelos;


    public Iterable<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(Iterable<Loja> lojas) {
        this.lojas = lojas;
    }

    public Iterable<Tamanho> getTamanhos() {
        return tamanhos;
    }

    public void setTamanhos(Iterable<Tamanho> tamanhos) {
        this.tamanhos = tamanhos;
    }

    public Iterable<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(Iterable<Modelo> modelos) {
        this.modelos = modelos;
    }
}
