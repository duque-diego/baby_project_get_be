package app.getfraldas.models;

import java.util.List;

/**
 * Created by diegods on 11/08/18 d.C..
 */
public class Preferencias {

    private String babyName;
    private String birthDate;
    private boolean checked;
    private String price;
    private String weight;
    private List<Fraldas> fraldas;
    private List<Tamanho> tamanhos;
    private List<Loja> lojas;

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public List<Fraldas> getFraldas() {
        return fraldas;
    }

    public void setFraldas(List<Fraldas> fraldas) {
        this.fraldas = fraldas;
    }

    public List<Tamanho> getTamanhos() {
        return tamanhos;
    }

    public void setTamanhos(List<Tamanho> tamanhos) {
        this.tamanhos = tamanhos;
    }

    public List<Loja> getLojas() {
        return lojas;
    }

    public void setLojas(List<Loja> lojas) {
        this.lojas = lojas;
    }
}
