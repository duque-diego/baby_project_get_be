package app.getfraldas.DTO;

import app.getfraldas.models.Promocao;
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
    private String unitPrice;
    private String packagePrice;
    private String promotionLink;
    private String imageLink;
    private String cupom;
    private Double discount;
    private Integer quantidade;

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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPromotionLink() {
        return promotionLink;
    }

    public void setPromotionLink(String promotionLink) {
        this.promotionLink = promotionLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public static PromocaoDTO toPromocaoDTO(Promocao promocao) {
        PromocaoDTO promocaoDTO = new PromocaoDTO();
        promocaoDTO.setId(promocao.getId());
        promocaoDTO.setLoja(LojaDTO.toLojaDTO(promocao.getLoja()));
        promocaoDTO.setPackagePrice("R$ " +promocao.getValorPacote().toString().replace(".",",")+checkIfHasDecimalPart(promocao.getValorPacote())+ "/pct");
        promocaoDTO.setUnitPrice("R$ "+promocao.getValorUnidade().toString().replace(".",",")+checkIfHasDecimalPart(promocao.getValorUnidade())+ "/un");
        promocaoDTO.setPromotionLink(promocao.getPromoLink());
        promocaoDTO.setImageLink(promocao.getImageLink());
        promocaoDTO.setCupom(promocao.getCupom());
        promocaoDTO.setDiscount(promocao.getDiscount());
        promocaoDTO.setFralda(FraldaDTO.toFraldaDTO(promocao.getModelo()));
        promocaoDTO.setTamanho(TamanhoDTO.toTamanhoDTO(promocao.getTamanho()));
        promocaoDTO.setQuantidade(promocao.getQuantidade());

        return promocaoDTO;
    }

    private static String checkIfHasDecimalPart (Double value){
        if(value % 1 == 0) {
            return "0";
        }
        return "";
    }

}
