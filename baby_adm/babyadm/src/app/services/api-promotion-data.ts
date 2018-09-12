
import { RequestMethod } from '@angular/http';
import { Injectable } from '@angular/core';
//import 'rxjs/add/operator/map';
import { RestApiProvider, IRestApiCall } from './rest-base.service';
import { SERVER_API_URL } from '../app.constants';
import { Promotion } from '../models/promotion';

/*
  Generated class for the ApiBeneficiarioProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ApiPromotionProvider {

  constructor(public restApiService:RestApiProvider) {
  }

  getStores() {
    let call: IRestApiCall = {
      baseUrl: SERVER_API_URL,
      endpoint: "/api/promocao",
      body: null,
      headers: {"Content-Type": "application/json"},
      method: RequestMethod.Get
    } 

    return this.restApiService.get(call);
  }

  putStore(promotion: Promotion){
    // let call: IRestApiCall = {
    //   baseUrl: SERVER_API_URL,
    //   endpoint: "/api/promocoes",
    //   body: null,
    //   headers: {"Content-Type": "application/json"},
    //   method: RequestMethod.Get
    // } 
    return this.restApiService.put(SERVER_API_URL+"/api/promocao", promotion);

  }

  getModel(promotionId:number){
    let call: IRestApiCall = {
      baseUrl: SERVER_API_URL,
      endpoint: "/api/promocao"+promotionId,
      body: null,
      headers: {"Content-Type": "application/json"},
      method: RequestMethod.Get
    } 

    return this.restApiService.get(call);

  }

  

}