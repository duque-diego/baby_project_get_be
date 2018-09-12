
import { RequestMethod } from '@angular/http';
import { Injectable } from '@angular/core';
import { RestApiProvider, IRestApiCall } from './rest-base.service';
import { SERVER_API_URL } from '../app.constants';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'; 
import 'rxjs/add/operator/catch';
import { Marca } from '../models/marca';

/*
  Generated class for the ApiBeneficiarioProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ApiBrandProvider {

  constructor(public restApiService:RestApiProvider) {
  }

  getBrands() : Observable<Marca[]>{
    let call: IRestApiCall = {
      baseUrl: SERVER_API_URL,
      endpoint: "/api/marcas",
      body: null,
      headers: {"Content-Type": "application/json"},
      method: RequestMethod.Get
    } 

    return this.restApiService.get(call).map((response:any) => parseJsonBrand(response));
  }

  putBrand(store: Marca){

    return this.restApiService.put(SERVER_API_URL+"/api/marca", store);
  }

}

function parseJsonBrand(data:any): Marca[]{

  let brands:Marca[] = [];
  data.forEach(product => {
    brands.push(product);
  });

  return brands;
}