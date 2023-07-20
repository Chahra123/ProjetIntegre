import { UserService } from './../service/user.service';
import { HttpClient } from '@angular/common/http';
import { UserAuthService } from './../service/user-auth.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import * as $ from 'jquery';
import { locale as english } from '../shared/i18n/en';
import { locale as french } from '../shared/i18n/fr';
import { AuthService } from '../service/auth.service';
import { SocieteService } from '../service/societe.service';
import jwt_decode from 'jwt-decode';
import { User } from '../_model/User';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  language:any="";
  loggedInUserName!:string;
  loggedInUser!:User;
  constructor(private _translateService: TranslateService,private authService: AuthService,private SocieteService: SocieteService, private router:Router , private userAuthService:UserAuthService, private httpClient: HttpClient, private userService:UserService) {

    if(localStorage.getItem("lang")){
      this.language=localStorage.getItem("lang");
    }
    else{
      this.language='fr';
    }
  }



  languageChange($event:any){
    let lang=$event.value;
    localStorage.setItem("lang", lang);
    this._translateService.setDefaultLang(lang);
    this._translateService.use(lang);
  }
  toggleLogin() {
    this.authService.setShowLogin(false);
    this.SocieteService.setShowSociete(true);
  }
  toggleSociete() {
    this.SocieteService.setShowSociete(false);
    this.authService.setShowLogin(true);
  }

  ngOnInit(): void {
    $('.js-scroll-trigger').on('click',
      function (): void {
        $('.navbar-collapse').toggle();
      }
    );
    $('.nav').on('click',
      function (): void {
        $('.navbar-collapse').toggle();
      }
    );
    const token = localStorage.getItem('jwtToken');
    console.log('Token:', token);
    if (token) {
      const decodedToken: any = jwt_decode(token);
      const email = decodedToken.sub;
      console.log('Email:', email);
      this.userService.getUserByEmail(email).subscribe(
        (data) => {
          console.log('User:', data);
          this.loggedInUser = data;
          this.loggedInUserName = `${data.prenom} ${data.nom}`;
          console.log('LoggedInUserName:', this.loggedInUserName);
        },
        (error) => {
          console.error('Error fetching user:', error);
        }
      );
    }
    else{
      this.loggedInUserName = "TEST"
    }

  }
  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }
  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/home']);
  }
}
