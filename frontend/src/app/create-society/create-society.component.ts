import { Component, OnInit } from '@angular/core';
import { Society } from '../_model/Society';
import { SocieteService } from '../service/societe.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-society',
  templateUrl: './create-society.component.html',
  styleUrls: ['./create-society.component.css']
})
export class CreateSocietyComponent implements OnInit {

  society: Society = new Society();
  constructor(private societeService: SocieteService, private router:Router) {}

  ngOnInit(): void {}
  onSubmit() {
    console.log(this.society);
    this.saveUser();
  }
  saveUser() {
    this.societeService.createSociety(this.society).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(['/societies']);
      },
      (error) => console.log(error)
    );
  }
  goToUserList()
  {
    this.router.navigate(['/societies']);
  }
  openModalCreate() {
   // this.modalService.open(this.modalContent, { centered: true });
  }

}
