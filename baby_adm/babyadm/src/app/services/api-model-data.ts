
import { RequestMethod } from '@angular/http';
import { Injectable } from '@angular/core';
//import 'rxjs/add/operator/map';
import { RestApiProvider, IRestApiCall } from './rest-base.service';
import { SERVER_API_URL } from '../app.constants';
import { Product } from '../models/product';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'; 
import 'rxjs/add/operator/catch';


/*
  Generated class for the ApiBeneficiarioProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ApiModelProvider {

  constructor(public restApiService:RestApiProvider) {
  }

  getModels(): Observable<Product[]> {
    let call: IRestApiCall = {
      baseUrl: SERVER_API_URL,
      endpoint: "/api/modelos",
      body: null,
      headers: {"Content-Type": "application/json"},
      method: RequestMethod.Get
    } 

    return this.restApiService.get(call).map((response:any) => parseJsonProduct(response));    
  }

  putModel(product: Product){
    // let call: IRestApiCall = {
    //   baseUrl: SERVER_API_URL,
    //   endpoint: "/api/promocoes",
    //   body: null,
    //   headers: {"Content-Type": "application/json"},
    //   method: RequestMethod.Get
    // } 
    return this.restApiService.put(SERVER_API_URL+"/api/modelo", product);

  }

  getModel(modelId:number){
    let call: IRestApiCall = {
      baseUrl: SERVER_API_URL,
      endpoint: "/api/modelo"+modelId,
      body: null,
      headers: {"Content-Type": "application/json"},
      method: RequestMethod.Get
    } 

    return this.restApiService.get(call);
  }

}

function parseJsonProduct(data:any): Product[]{

  let products:Product[] = [];
  data.forEach(product => {
    products.push(product);
  });
  //let product:UserModel = new UserModel(data.id, data.nome, data.email, data.telefone, data.cpf, data.senha);
  return products;
}