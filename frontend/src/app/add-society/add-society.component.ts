import { PopupConfirmSocietyCreationComponent } from '../popup-confirm-society-creation/popup-confirm-society-creation.component';
import { Society } from './../_model/Society';
import { SocieteService } from './../service/societe.service';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog'

@Component({
  selector: 'app-add-society',
  templateUrl: './add-society.component.html',
  styleUrls: ['./add-society.component.css']
})
export class AddSocietyComponent implements OnInit {

  society:Society= new Society();
  constructor(private societeService:SocieteService, private matDialog:MatDialog) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.society);
    this.saveSociety();
    this.openDialogSuccessfullCreation();
  }

  saveSociety()
  {
    this.societeService.createSociety(this.society).subscribe
    (data => console.log(data),
     error => console.log(error));
    this.society = new Society();
  }

  openDialogSuccessfullCreation()
  {
    this.matDialog.open(PopupConfirmSocietyCreationComponent,{
      width : '500px',
    })
  }


}
