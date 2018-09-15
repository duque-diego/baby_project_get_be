import { Component, OnInit } from '@angular/core';
import { ApiBrandProvider } from '../services/api-brand-data';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-brand-list',
  templateUrl: './brand-list.component.html',
  styleUrls: ['./brand-list.component.css']
})
export class BrandListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name'];
  dataSource:any; 
  showSpinner:boolean;

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  constructor(public apiBrandProvider:ApiBrandProvider) { 

    this.showSpinner = true;
    this.apiBrandProvider
        .getBrands()
        .subscribe(response => {
          this.dataSource = new MatTableDataSource(response);
          this.showSpinner = false;
        }, error => {
          this.showSpinner = false;
          console.log("erro");
        });
  }

  ngOnInit() {
  }

}
