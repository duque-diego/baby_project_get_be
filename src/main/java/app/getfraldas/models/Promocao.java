package app.getfraldas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by fprado on 03/09/18
 */

@Entity
@Table(name = "promocao")
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "valorUnidade", nullable = false)
    private Double valorUnidade;

    @NotNull
    @Column(name = "valorPacote", nullable = false)
    private Double valorPacote;

    @NotNull
    @Column(name = "promoLink", nullable = false)
    private String promoLink;

    @JsonIgnore
    @NotNull
    @Column(name = "lastUpdate")
    private Date lastUpdate;

    @Column(name = "imageLink")
    private String imageLink;

    @ManyToOne
    private Modelo modelo;

    @ManyToOne
    private Tamanho tamanho;

    @ManyToOne
    private Loja loja;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorUnidade() {
        return valorUnidade;
    }

    public void setValorUnidade(Double valorUnidade) {
        this.valorUnidade = valorUnidade;
    }

    public Double getValorPacote() {
        return valorPacote;
    }

    public void setValorPacote(Double valorPacote) {
        this.valorPacote = valorPacote;
    }

    public String getPromoLink() {
        return promoLink;
    }

    public void setPromoLink(String promoLink) {
        this.promoLink = promoLink;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
