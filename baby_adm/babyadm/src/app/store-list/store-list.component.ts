import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Store } from '../models/store';

@Component({
  selector: 'app-store-list',
  templateUrl: './store-list.component.html',
  styleUrls: ['./store-list.component.css']
})
export class StoreListComponent implements OnInit {

  ELEMENT_DATA: Store[] = [
    new Store(1, 'Americanas', null),
    new Store(2, 'Magazine Luiza', null),
    new Store(3, 'Onofre', null),
    new Store(4, 'Wallmart', null),
    new Store(5, 'Carrefour', null),
  ];

  displayedColumns: string[] = ['id', 'name', 'imgSrc'];
  dataSource = new MatTableDataSource(this.ELEMENT_DATA);

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor() { }

  ngOnInit() {
  }

}
