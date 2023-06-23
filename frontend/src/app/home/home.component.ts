import { Component, OnInit } from '@angular/core';
import { TranslationLoaderService } from '../service/translation-loader.service';
import Typed from 'typed.js';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private _translationLoaderService: TranslationLoaderService) {
 
  }
  ngOnInit(): void {
    var options = {
      strings: ['To The Fullest'],
      typeSpeed: 120,
      backSpeed: 100,
      loop: true,
    };

    var typed = new Typed('.typed', options);
    typed.reset(true)
  }
}
