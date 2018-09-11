package app.getfraldas.DTO;

import app.getfraldas.models.Loja;
import app.getfraldas.models.Marca;
import app.getfraldas.models.Tamanho;

/**
 * Created by fprado on 10/09/18
 */
public class PreferenciasDTO {
    private Iterable<Loja> lojas;
    private Iterable<Tamanho> tamanhos;
    private Iterable<Marca> marcas;

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

    public Iterable<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(Iterable<Marca> marcas) {
        this.marcas = marcas;
    }
}
