import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product';
import { MatTableDataSource } from '@angular/material';
import { ApiModelProvider } from '../services/api-model-data';

export interface IProduct {
  id: number;
  name: string;
  brandId: number;
  imgSrc: string;
}


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'brandId', 'imgSrc'];
  dataSource:any;

  constructor(public apiModelProvider:ApiModelProvider) {

    this.apiModelProvider
        .getModels()
        .subscribe(response => {
          this.dataSource = new MatTableDataSource(response);
        }, error => {
          console.log("erro");
        });
  }

  ngOnInit() {
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
