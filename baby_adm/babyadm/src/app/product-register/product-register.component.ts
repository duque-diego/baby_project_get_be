import { Component, OnInit } from '@angular/core';
import { Brand } from '../models/brand';
import { Size } from '../models/size';
import { Product } from '../models/product';

@Component({
  selector: 'app-product-register',
  templateUrl: './product-register.component.html',
  styleUrls: ['./product-register.component.css']
})
export class ProductRegisterComponent implements OnInit {

  url = '';
  private brands:Brand[] = [
    new Brand(1, 'Pampers'),
    new Brand(2, 'MammyPoko'),
    new Brand(3, 'Johnsons Baby'),
    new Brand(4, 'Turmar da Mônica'),
    new Brand(5, 'Huggies'),
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
  productBrand:number;
  productImg:string;

  ourFile: File; // hold our file

  constructor() { }

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
  }

}
