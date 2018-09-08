import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Promotion } from '../models/promotion';

@Component({
  selector: 'app-promotion-list',
  templateUrl: './promotion-list.component.html',
  styleUrls: ['./promotion-list.component.css']
})
export class PromotionListComponent implements OnInit {

 

  ELEMENT_DATA: Promotion[] = [
    new Promotion(1, 12, 45, 'adfadfadfadfadf', 1, 2, 3),
    new Promotion(2, 12, 45, 'adfadfadfadfadf', 1, 2, 3),
    new Promotion(3, 12, 45, 'adfadfadfadfadf', 1, 2, 3),
    new Promotion(4, 12, 45, 'adfadfadfadfadf', 1, 2, 3),
    new Promotion(5, 12, 45, 'adfadfadfadfadf', 1, 2, 3),
  ];

  displayedColumns: string[] = ['id', 'unitValue', 'packageValue', 'promotionLink', 'productId', 'sizeId', 'storeId'];
  dataSource = new MatTableDataSource(this.ELEMENT_DATA);

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor() { }

  ngOnInit() {
  }

}
