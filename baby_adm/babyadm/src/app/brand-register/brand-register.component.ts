import { Component, OnInit } from '@angular/core';
import { ApiBrandProvider } from '../services/api-brand-data';
import { Marca } from '../models/marca';

@Component({
  selector: 'app-brand-register',
  templateUrl: './brand-register.component.html',
  styleUrls: ['./brand-register.component.css']
})
export class BrandRegisterComponent implements OnInit {

  brandName:any;
  constructor(public apiBrandProvider:ApiBrandProvider) { }

  ngOnInit() {
  }

  register(){
    var brand = new Marca(null, this.brandName);
    console.log(brand);
    this.apiBrandProvider
        .putBrand(brand)
        .subscribe(response => {
          console.log(response);
        }, error => {
          console.log(error);
        });
  }

}
