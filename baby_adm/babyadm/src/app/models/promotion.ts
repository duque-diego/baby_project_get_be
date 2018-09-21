export class Promotion {
    id: number;
    valorUnidade: number;
    valorPacote: number;
    promoLink: string;
    modelo: number;
    tamanho: number;
    loja: number;
    imageLink: string;
    cupom: string;
    discount: number;

    constructor(
      id: number, 
      valorUnidade: number, 
      valorPacote: number, 
      promoLink: string, 
      modelo: number, 
      tamanho: number,
      loja:number,
      imageLink: string,
      cupom: string,
      discount: number
    ) 
    {
      this.id = id;
      this.valorUnidade = valorUnidade;
      this.valorPacote = valorPacote;
      this.promoLink = promoLink;
      this.modelo = modelo;
      this.tamanho = tamanho;
      this.loja = loja;
      this.imageLink = imageLink;
      this.cupom = cupom;
      this.discount = discount;
    }
}