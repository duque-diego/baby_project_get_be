import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product';
import { Size } from '../models/size';
import { Loja } from '../models/store';
import { Promotion } from '../models/promotion';
import { ApiPromotionProvider } from '../services/api-promotion-data';
import { Router } from '@angular/router';

@Component({
  selector: 'app-promotion-register',
  templateUrl: './promotion-register.component.html',
  styleUrls: ['./promotion-register.component.css']
})
export class PromotionRegisterComponent implements OnInit {

  url = '';
  productValuePackage:number;
  packageQuantity:number;
  productValueUnit:number;
  promotionLink:string;
  product:number;
  productSize:number;
  productStore:number;
  productCupom: string;
  productDiscount: number;

  private stores:Loja[] = [];
  private products:Product[] = [];
  private sizes:Size[] = [];

  constructor(public apiPromotionProvider:ApiPromotionProvider, public router:Router) {
    
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
      this.productStore,
      this.url,
      this.productCupom,
      this.productDiscount
    );
    console.log(promotion);
    this.apiPromotionProvider
      .putPromotion(promotion)
      .subscribe(response => {
        console.log(response);
        this.router.navigate(['promotion-list']);
      }, error => {
        console.log("erro");
      });
  }

  calculateUnitValue() {
    this.productValuePackage && this.packageQuantity ? this.productValueUnit = this.productValuePackage / this.packageQuantity : "";
  }

  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (event) => { // called once readAsDataURL is completed
        this.url = event.target.result;
      }
    }
  }

  openInput() {
    document.getElementById("fileInput").click();
  }

}
