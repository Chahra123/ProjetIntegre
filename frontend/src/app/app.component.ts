import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Observable } from 'rxjs';
import { AuthService } from './service/auth.service';
import { SocieteService } from './service/societe.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})


export class AppComponent implements OnInit {
  currentYear:number;
  showLogin: boolean = false;
  showSociete: boolean = true;
  constructor(private _translateService: TranslateService,private SocieteService: SocieteService,private authService: AuthService,private router: Router) {
    this._translateService.addLangs(['en', 'fr']);
    this._translateService.setDefaultLang('fr');
    this.currentYear=new Date().getFullYear();
  }

  ngOnInit(): void {
    if (!localStorage.getItem("lang")) {
      localStorage.setItem("lang", 'en');
    }
    let lang:any=localStorage.getItem("lang"); 
    this._translateService.setDefaultLang(lang);
    this._translateService.use(lang);

    this.authService.showLogin$.subscribe((showLogin) => {
      this.showLogin = showLogin;
    });

    this.SocieteService.showSociete$.subscribe((showSociete) => {
      this.showSociete = showSociete;
    });

  }

}
