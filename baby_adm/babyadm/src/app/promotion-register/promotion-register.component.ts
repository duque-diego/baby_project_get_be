import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product';
import { Size } from '../models/size';
import { Loja } from '../models/store';
import { Promotion } from '../models/promotion';
import { ApiStoreProvider } from '../services/api-store-data';
import { ApiModelProvider } from '../services/api-model-data';

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


  constructor(public apiStoreProvider:ApiStoreProvider, public apiModelProvider:ApiModelProvider) {
    this.apiStoreProvider
        .getStores()
        .subscribe(response => {
          this.stores = response;
        }, error => {
          console.log("erro");
        });
    
    this.apiModelProvider
    .getModels()
    .subscribe(response => {
      this.products = response;
    }, error => {
      console.log("erro");
    });

   }

  ngOnInit() {
  }
  private sizes:Size[] = [
    new Size(1, 'P'),
    new Size(2, 'M'),
    new Size(3, 'G'),
    new Size(4, 'GG'),
    new Size(5, 'XXG'),
  ];

  

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
  }

}
