import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { LoginComponent } from '../app/login/login.component';
import { DashboardComponent } from '../app/dashboard/dashboard.component';
import { ProductRegisterComponent } from '../app/product-register/product-register.component';
import { StoreRegisterComponent } from '../app/store-register/store-register.component';
import { PromotionRegisterComponent } from '../app/promotion-register/promotion-register.component';
import { ProductListComponent } from '../app/product-list/product-list.component';
import { PromotionListComponent } from '../app/promotion-list/promotion-list.component';
import { StoreListComponent } from '../app/store-list/store-list.component';

const routes: Routes = [
    { path: 'products', component: ProductListComponent },
    { path: 'login', component: LoginComponent },
    { path: 'product-register', component: ProductRegisterComponent },
    { path: 'product-list', component: ProductListComponent },
    { path: "dashboard", component: DashboardComponent },
    { path: "store-register", component: StoreRegisterComponent },
    { path: "store-list", component: StoreListComponent },
    { path: "promotion-register", component: PromotionRegisterComponent },
    { path: "promotion-list", component: PromotionListComponent },
    { path: '',  redirectTo: '/products', pathMatch: 'full' }
];

export const routingModule: ModuleWithProviders = RouterModule.forRoot(routes);