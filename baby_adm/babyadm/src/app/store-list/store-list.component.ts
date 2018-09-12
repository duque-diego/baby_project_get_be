import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Loja } from '../models/store';
import { ApiStoreProvider } from '../services/api-store-data';

@Component({
  selector: 'app-store-list',
  templateUrl: './store-list.component.html',
  styleUrls: ['./store-list.component.css']
})
export class StoreListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'imgSrc'];
  dataSource:any; 

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor(public apiStoreProvider:ApiStoreProvider) { 

    this.apiStoreProvider
        .getStores()
        .subscribe(response => {
          this.dataSource = new MatTableDataSource(response);
        }, error => {
          console.log("erro");
        });
  }

  ngOnInit() {
  }

}
