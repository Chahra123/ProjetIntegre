import { EventService } from './../service/event.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { SocieteService } from '../service/societe.service';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Society } from '../_model/Society';
import { Evenement } from '../_model/Evenement';

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
  constructor(private societeService:SocieteService, private router:Router, private eventService:EventService) { }

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

  toggleRegistration(){
    if (this.showRegistration==false) {
      this.showRegistration=true
    }else{
      this.showRegistration=false
    }
    if (  this.showSociete==false) {
      this.showSociete=true
    }else{
      this.showSociete=false
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
    this.openModalDelete();
   })
  }
  viewSociety(id:number)
  {
    this.router.navigate(['users/details',id]);
  }
  addSociety() {
    this.router.navigate(['/users/create']);
  }
  openModalDelete() {
   // this.modalService.open(this.modalContent, { centered: true });
  }

  confirmDeleteSociety(userId:number) {
    if (confirm("Voulez-vous vraiment supprimer la société?")) {
      this.deleteSociety(userId);
    }
  }

 // Function to initialize the eventsForSociety array with empty arrays
 private initEventsForSociety(): void {
  this.eventsForSociety = this.societies.map(() => []);
}

// Function to fetch events for a specific society and store them in the eventsForSociety array
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

}

