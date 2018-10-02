import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Promotion } from '../models/promotion';
import { ApiPromotionProvider } from '../services/api-promotion-data';
import { Router } from '@angular/router';

@Component({
  selector: 'app-promotion-list',
  templateUrl: './promotion-list.component.html',
  styleUrls: ['./promotion-list.component.css']
})
export class PromotionListComponent implements OnInit {

  ELEMENT_DATA: Promotion[] = [];
  displayedColumns: string[] = ['id',  'productId', 'sizeId', 'packageValue', 'quantidade','unitValue','cupom', 'desconto',  'storeId', 'editar'];
  dataSource; 

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor(public apiPromotionProvider:ApiPromotionProvider, public router:Router) {
    this.apiPromotionProvider
      .getPromotions()
      .subscribe(response => {
        this.ELEMENT_DATA = response;
        this.dataSource = new MatTableDataSource(this.ELEMENT_DATA);
      }, error => {
        console.log("erro");
      });
  }

  ngOnInit() {
  }

  editar(id){
    this.router.navigate(['promotion-register', id]);
  }

}
