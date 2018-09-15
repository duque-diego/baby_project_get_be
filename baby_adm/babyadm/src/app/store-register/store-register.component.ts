import { Component, OnInit } from '@angular/core';
import { Loja } from '../models/store';
import { ApiStoreProvider } from '../services/api-store-data';
import { Router } from '@angular/router';

@Component({
  selector: 'app-store-register',
  templateUrl: './store-register.component.html',
  styleUrls: ['./store-register.component.css']
})
export class StoreRegisterComponent implements OnInit {

  url = '';
  storeName:string = '';
  showSpinner:boolean;
  constructor(public apiStoreProvider:ApiStoreProvider, public router:Router) { }

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
    var store = new Loja(null, this.storeName, this.url);
    console.log(store);
    this.showSpinner = true;
    this.apiStoreProvider
        .putStore(store)
        .subscribe(response => {
          this.showSpinner = false;
          this.router.navigate(['store-list']);
          console.log(response);
        }, error => {
          this.showSpinner = false;
          console.log(error);
        });
  }

}
