export class Loja {
    id: number;
    nome: string;
    imageLink: string;
    constructor(id: number, nome: string, imageLink:string) {
      this.id = id;
      this.nome = nome;
      this.imageLink = imageLink;
    }
}