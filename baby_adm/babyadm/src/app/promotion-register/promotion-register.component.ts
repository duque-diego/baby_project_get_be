import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product';
import { Size } from '../models/size';
import { Store } from '../models/store';
import { Promotion } from '../models/promotion';

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


  constructor() { }

  ngOnInit() {
  }

  private products:Product[] = [
    new Product(1, 'Pampers', 1 , null),
    new Product(2, 'Huggies', 1 , null),
    new Product(3, 'Comfort', 1 , null),
    new Product(4, 'Bebe seco', 1 , null),
    new Product(5, 'Bebe molhado', 1 , null),
  ];

  private sizes:Size[] = [
    new Size(1, 'P'),
    new Size(2, 'M'),
    new Size(3, 'G'),
    new Size(4, 'GG'),
    new Size(5, 'XXG'),
  ];

  private stores:Store[] = [
    new Store(1, 'Americanas', null),
    new Store(2, 'Magazine Luiza', null),
    new Store(3, 'Onofre', null),
    new Store(4, 'Wallmart', null),
    new Store(5, 'Carrefour', null),
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
