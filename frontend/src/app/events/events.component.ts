import { Component, OnInit } from '@angular/core';

import { locale as english } from '../shared/i18n/en';
import { locale as french } from '../shared/i18n/fr';
import { projectsEn} from '../api/projectsEn';
import { projectsFr} from '../api/projectsFr';
import { MatDialog } from '@angular/material/dialog'
import { TranslateService } from '@ngx-translate/core';
import { ReservationDialogComponent } from '../reservation-dialog/reservation-dialog.component';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {

  projects:any[]=projectsEn;
  venobox: any;

  filteredProjects: any[] = [];
  filteredProjects1: any[] = [];
  constructor(private _translateService: TranslateService, private matDialog: MatDialog) {

    this._translateService.onLangChange.subscribe(()=>{
      if(this._translateService.currentLang=="en"){
        this.projects=projectsEn;
      }
      else{
        this.projects=projectsFr;
      }
    });
  }
  ngOnInit(): void {
    this.filteredProjects=this.projects
    this.filteredProjects1=this.filteredProjects
  }

  DateSelected:any;
  LieuSelected: string = '';;
  fetchDateSelected(){
    if (this.DateSelected!=undefined && this.LieuSelected!="") {
      this.filteredProjects = this.projects.filter(project => project.lieu === this.LieuSelected && project.date === this.DateSelected);  
      console.log("1")
    }else if(this.LieuSelected=="" && this.DateSelected!=undefined){
      this.filteredProjects = this.projects.filter(project => project.date === this.DateSelected);
      console.log("2")  
    }else if(this.LieuSelected=="" && this.DateSelected==undefined){
      
      this.filteredProjects=this.projects
      console.log("3")
    }else if(this.LieuSelected!="" && this.DateSelected==undefined){
      this.filteredProjects = this.projects.filter(project => project.lieu === this.LieuSelected);  
      console.log("4")
    }
  }

  fetchLieuSelected(){
    if (this.DateSelected!=undefined && this.LieuSelected!="") {
      this.filteredProjects = this.projects.filter(project => project.lieu === this.LieuSelected && project.date === this.DateSelected);  
      console.log("1")
    }else if(this.LieuSelected=="" && this.DateSelected!=undefined){
      this.filteredProjects = this.projects.filter(project => project.date === this.DateSelected);
      console.log("2")  
    }else if(this.LieuSelected=="" && this.DateSelected==undefined){
      
      this.filteredProjects=this.projects
      console.log("3")
    }else if(this.LieuSelected!="" && this.DateSelected==undefined){
      this.filteredProjects = this.projects.filter(project => project.lieu === this.LieuSelected);  
      console.log("4")
    }
    
  
  }
  ngAfterViewInit(){
    console.log("after view init");
    this.onMouse("portfolio-link", "portfolio-img");
    this.onMouse("teamTraveler-link", "teamTraveler-img");
    this.onMouse("mautic-link", "mautic-img");
    this.onMouse("pokedex-link", "pokedex-img");
    this.onMouse("riddle-link", "riddle-img");
    this.venobox = $('.venobox');
    this.venobox.venobox();
  }

  detailOnClick(project: any) {
    this.projects.filter(item=>item.detailIsDisplayed && item.id!=project.id).map(elem=>elem.detailIsDisplayed=false);
    project.detailIsDisplayed=!project.detailIsDisplayed;
  }

  openDialog(){
    this.matDialog.open(ReservationDialogComponent,{
      width : '350px',
    })
  }

  onMouse(idLink: String, idImage: String) {
    $('#' + idLink).on("mouseenter", function () {
      console.log('on mouseenter');
      $('#' + idImage).css("opacity", "0.3");
      $('#' + idLink).css("opacity", "1");
    }).on('mouseleave', function () {
      $('#' + idImage).css("opacity", "1");
      $('#' + idLink).css("opacity", "0");
    }
    );

    $('#' + idImage).on("mouseenter", function () {
      $('#' + idImage).css("opacity", "0.3");
      $('#' + idLink).css("opacity", "1");
    }).on('mouseleave', function () {
      $('#' + idImage).css("opacity", "1");
      $('#' + idLink).css("opacity", "0");
    }
    );
  }
}
