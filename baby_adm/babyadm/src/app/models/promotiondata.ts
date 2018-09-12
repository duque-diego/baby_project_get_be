import { Product } from "./product";
import { Size } from "./size";
import { Loja } from "./store";

export class PromotionData {
    modelos: Product[];
    tamanhos: Size[];
    lojas: Loja[];
    constructor(modelos: Product[], tamanhos: Size[], lojas: Loja[]){
        this.modelos = modelos;
        this.tamanhos = tamanhos;
        this.lojas = lojas;
    }
}