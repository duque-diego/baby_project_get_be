import { Component, OnInit } from '@angular/core';
import { Marca } from '../models/marca';
import { Size } from '../models/size';
import { Product } from '../models/product';
import { ApiModelProvider } from '../services/api-model-data';

@Component({
  selector: 'app-product-register',
  templateUrl: './product-register.component.html',
  styleUrls: ['./product-register.component.css']
})
export class ProductRegisterComponent implements OnInit {

  url = '';
  private brands:Marca[] = [
    new Marca(1, 'Pampers'),
    new Marca(2, 'MammyPoko'),
    new Marca(3, 'Johnsons Baby'),
    new Marca(4, 'Turmar da Mônica'),
    new Marca(5, 'Huggies'),
  ];

  private sizes:Size[] = [
    new Size(1, 'P'),
    new Size(2, 'M'),
    new Size(3, 'G'),
    new Size(4, 'GG'),
    new Size(5, 'XXG'),
  ];

  productName:string = '';
  productSize:number;
  productBrand:Marca;
  productImg:string;
  marca:Marca;

  ourFile: File; // hold our file

  constructor(public apiModelProvider:ApiModelProvider) { }

  ngOnInit() {
  }

  openInput(){ 
    
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
          console.log(response);
          // this.storage.set("userData", response); 
          // loading.dismiss();
          // this.presentConfirm();
        }, error => {
          //loading.dismiss();
        });
  }

}
