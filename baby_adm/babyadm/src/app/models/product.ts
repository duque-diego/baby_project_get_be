import { Marca } from "./marca";

export class Product {
    id: number;
    nome: string;
    marca: Marca;
    imageLink: string;
    constructor(id: number, nome: string, marca:Marca, imageLink:string) {
      this.id = id;
      this.nome = nome;
      this.marca = marca;
      this.imageLink = imageLink;
    }
}