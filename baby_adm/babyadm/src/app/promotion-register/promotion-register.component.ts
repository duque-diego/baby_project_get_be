import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product';
import { Size } from '../models/size';
import { Loja } from '../models/store';
import { Promotion } from '../models/promotion';
import { ApiPromotionProvider } from '../services/api-promotion-data';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup,FormControl,Validators } from '@angular/forms';

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
  promotionGroup: FormGroup;

  private stores:Loja[] = [];
  private products:Product[] = [];
  private sizes:Size[] = [];
  private promotion:Promotion;
  private id:any;
  private isEdicao:boolean = false;

  constructor(public apiPromotionProvider:ApiPromotionProvider, private fb: FormBuilder, public route:ActivatedRoute, public router:Router) {
    

    this.route.params.subscribe(params => {
        this.id = +params['id']; 
        if(this.id){
          this.isEdicao = true;
          this.apiPromotionProvider
            .getPromotionData()
            .subscribe(response => {
              this.stores = response.lojas;
              this.products = response.modelos;
              this.sizes = response.tamanhos;
              this.apiPromotionProvider
                .getPromotionById(this.id)
                .subscribe(response => {
                  console.log(response);
                  //this.promotion = response;
                  this.productValuePackage = response.valorPacote;
                  this.packageQuantity = response.quantidade;
                  this.productValueUnit = response.valorUnidade;
                  this.promotionLink = response.promoLink;
                  this.productCupom = response.cupom;
                  this.productDiscount = response.discount;
                  this.product = response.modelo;
                  this.productSize = response.tamanho;
                  this.productStore = response.loja;
                  this.url = response.imageLink;
              })
            }, error => {
              console.log("erro");
          });
          
        }else{
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
      this.productDiscount,
      this.packageQuantity
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

  update(){
    var promotion = new Promotion(
      this.id, 
      this.productValueUnit, 
      this.productValuePackage, 
      this.promotionLink, 
      this.product, 
      this.productSize, 
      this.productStore,
      this.url,
      this.productCupom,
      this.productDiscount,
      this.packageQuantity
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

}
