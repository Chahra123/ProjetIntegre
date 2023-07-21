import { AddSocietyComponent } from './../add-society/add-society.component';
import { EventService } from './../service/event.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { SocieteService } from '../service/societe.service';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Society } from '../_model/Society';
import { Evenement } from '../_model/Evenement';
import { MatDialog } from '@angular/material/dialog'


@Component({
  selector: 'app-societe',
  templateUrl: './societe.component.html',
  styleUrls: ['./societe.component.css'],
})
export class SocieteComponent implements OnInit {
  showSociete: boolean = true;
  showRegistration: boolean = false;
  isOpen: boolean[] = [];
  societies:Society[]=[];
  eventsForSociety: Evenement[][] = [];
  constructor(private societeService:SocieteService, private router:Router, private eventService:EventService, private matDialog:MatDialog) { }

  ngOnInit(): void {
    this.getSocieties();
    this.societeService.showSociete$.subscribe((showSociete) => {
      this.showSociete = showSociete;
    });
    this.societeService.showRegistration$.subscribe((showRegistration) => {
      this.showRegistration = showRegistration;
    });

    const numTables = 2; // Update the number based on the actual number of tables
    this.isOpen = Array(numTables).fill(false);
  }
  toggleTable(index: number) {
    this.isOpen[index] = !this.isOpen[index];
    if (this.isOpen[index] && !this.eventsForSociety[index]) {
      // Check for truthiness of eventsForSociety[index] instead of its length
      this.getEventsForSociety(this.societies[index].idSociete, index);
    }
  }


  private getSocieties() {
    this.societeService.getSocietiesList().subscribe((data) => {
      this.societies = data;
    });
  }
  updateSociety(id: number) {
    this.router.navigate(['societies/update', id]);
  }
  deleteSociety(id:number)
  {
   this.societeService.deleteSociety(id).subscribe(data=>{
    console.log(data);
    this.getSocieties();
   })
  }
  viewSociety(id:number)
  {
    this.router.navigate(['users/details',id]);
  }
  addSociety() {
    this.router.navigate(['/users/create']);
  }

  confirmDeleteSociety(userId:number) {
    if (confirm("Voulez-vous vraiment supprimer la société?")) {
      this.deleteSociety(userId);
    }
  }

private getEventsForSociety(societyId: number, index: number): void {
  this.eventService.getEventsForSociety(societyId).subscribe(
    (events: Evenement[]) => {
      this.eventsForSociety[index] = events;
    },
    (error) => {
      console.error('Error fetching events for society:', error);
    }
  );
}
openDialog(){
  this.matDialog.open(AddSocietyComponent,{
    width : '500px',
  })
}

confirmDeleteUser(userId:number) {
  if (confirm("Do you really want to delete the society?")) {
    this.deleteSociety(userId);
  }
}

}
