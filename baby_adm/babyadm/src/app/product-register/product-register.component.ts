import { Component, OnInit } from '@angular/core';
import { Marca } from '../models/marca';
import { Size } from '../models/size';
import { Product } from '../models/product';
import { ApiModelProvider } from '../services/api-model-data';
import { Router } from "@angular/router";
import { ApiBrandProvider } from '../services/api-brand-data';

@Component({
  selector: 'app-product-register',
  templateUrl: './product-register.component.html',
  styleUrls: ['./product-register.component.css']
})
export class ProductRegisterComponent implements OnInit {

  url = '';
  private brands:Marca[] = [];

  private sizes:Size[] = [];

  productName:string = '';
  productSize:number;
  productBrand:Marca;
  productImg:string;
  marca:Marca;

  ourFile: File; // hold our file

  constructor(public apiModelProvider:ApiModelProvider, public router:Router, public apiBrandProvider:ApiBrandProvider ) { 

    this.apiBrandProvider
        .getBrands()
        .subscribe(response => {
          this.brands = response;
        }, error => {
          console.log("erro");
        });

  }

  ngOnInit() {
  }

  openInput() {
    document.getElementById("fileInput").click();
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

  register(){
    var product = new Product(null, this.productName, this.productBrand, this.url);
    console.log(product);
    this.apiModelProvider
        .putModel(product)
        .subscribe(response => {
          this.router.navigate(['product-list']);
          console.log(response);
        }, error => {
          console.log(error);
        });
  }

}
