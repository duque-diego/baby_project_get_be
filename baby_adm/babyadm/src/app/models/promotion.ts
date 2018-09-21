export class Promotion {
    id: number;
    valorUnidade: number;
    valorPacote: number;
    promoLink: string;
    modelo: number;
    tamanho: number;
    loja: number;
    imageLink: string;
    constructor(
      id: number, 
      valorUnidade: number, 
      valorPacote: number, 
      promoLink: string, 
      modelo: number, 
      tamanho: number,
      loja:number,
      imageLink: string
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
    }
}