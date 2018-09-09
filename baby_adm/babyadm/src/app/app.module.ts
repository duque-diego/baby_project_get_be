import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MatGridListModule, MatCardModule, MatMenuModule, MatIconModule, MatButtonModule, MatToolbarModule, MatSidenavModule, MatListModule, MatTableModule, MatPaginatorModule, MatSortModule, MatSelectModule } from '@angular/material';
import { LayoutModule } from '@angular/cdk/layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule,MatInputModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { ProductListComponent } from './product-list/product-list.component';

import { routingModule } from './app.routing';
import { LoginComponent } from './login/login.component';
import { ProductRegisterComponent } from './product-register/product-register.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { StoreRegisterComponent } from './store-register/store-register.component';
import { PromotionRegisterComponent } from './promotion-register/promotion-register.component';
import { PromotionListComponent } from './promotion-list/promotion-list.component';
import { StoreListComponent } from './store-list/store-list.component';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ProductListComponent,
    LoginComponent,
    ProductRegisterComponent,
    ToolbarComponent,
    StoreRegisterComponent,
    PromotionRegisterComponent,
    PromotionListComponent,
    StoreListComponent
  ],
  imports: [
    BrowserModule,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    BrowserModule, 
    BrowserAnimationsModule,
    MatButtonModule, 
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    routingModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }