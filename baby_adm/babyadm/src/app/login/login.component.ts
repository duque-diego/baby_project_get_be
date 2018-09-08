import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  nomeUsuario: string = '';
  senha: string = '';

  doLogin(){
    if(this.nomeUsuario == 'ademir' && this.senha == 'ademir'){
      this.router.navigate(['dashboard']);
    }
  }

  constructor(private router: Router) { }

  ngOnInit() {
  }

}
