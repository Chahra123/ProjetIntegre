import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import * as $ from 'jquery';
import { AuthService } from '../service/auth.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  language:any="";
  constructor(private _translateService: TranslateService,private authService: AuthService) {

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
  }
}
