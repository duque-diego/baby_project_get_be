import { Component, OnInit } from '@angular/core';
import { Product } from '../models/product';
import { MatTableDataSource } from '@angular/material';

export interface IProduct {
  id: number;
  name: string;
  brandId: number;
  imgSrc: string;
}

const ELEMENT_DATA: IProduct[] = [
  {id: 1, name: 'Pampers', brandId: 1 , imgSrc: null},
  {id: 2, name: 'Huggies', brandId: 1 , imgSrc: null},
  {id: 3, name: 'Turma Monica', brandId: 1 , imgSrc: null},
  {id: 4, name: 'Johnsons', brandId: 1 , imgSrc: null},
  {id: 5, name: 'Teste', brandId: 1 , imgSrc: null},
];

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'brandId', 'imgSrc'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor() { }

  ngOnInit() {
  }

}
