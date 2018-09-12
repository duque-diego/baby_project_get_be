import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product';
import { Size } from '../models/size';
import { Loja } from '../models/store';
import { Promotion } from '../models/promotion';
import { ApiPromotionProvider } from '../services/api-promotion-data';
;

@Component({
  selector: 'app-promotion-register',
  templateUrl: './promotion-register.component.html',
  styleUrls: ['./promotion-register.component.css']
})
export class PromotionRegisterComponent implements OnInit {

  productValueUnit:number;
  productValuePackage:number;
  promotionLink:string;
  product:number;
  productSize:number;
  productStore:number;
  private stores:Loja[] = [];
  private products:Product[] = [];
  private sizes:Size[] = [];

  constructor(public apiPromotionProvider:ApiPromotionProvider) {
    
    this.apiPromotionProvider
      .getPromotionData()
      .subscribe(response => {
        this.stores = response.lojas;
        this.products = response.modelos;
        this.sizes = response.tamanhos;
      }, error => {
        console.log("erro");
      });
   }

  ngOnInit() {}
  
  register(){
    var promotion = new Promotion(
      null, 
      this.productValueUnit, 
      this.productValuePackage, 
      this.promotionLink, 
      this.product, 
      this.productSize, 
      this.productStore
    );
    console.log(promotion);
    this.apiPromotionProvider
      .putPromotion(promotion)
      .subscribe(response => {
        console.log(response);
      }, error => {
        console.log("erro");
      });
  }

}
