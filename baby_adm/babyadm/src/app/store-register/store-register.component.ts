import { Component, OnInit } from '@angular/core';
import { Loja } from '../models/store';
import { ApiStoreProvider } from '../services/api-store-data';

@Component({
  selector: 'app-store-register',
  templateUrl: './store-register.component.html',
  styleUrls: ['./store-register.component.css']
})
export class StoreRegisterComponent implements OnInit {

  url = '';
  storeName:string = '';
  constructor(public apiStoreProvider:ApiStoreProvider) { }

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

    this.apiStoreProvider
        .putStore(store)
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
