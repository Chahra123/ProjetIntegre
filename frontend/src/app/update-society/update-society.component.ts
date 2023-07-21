import { ActivatedRoute, Router } from '@angular/router';
import { SocieteService } from './../service/societe.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../_model/User';
import { Society } from '../_model/Society';

@Component({
  selector: 'app-update-society',
  templateUrl: './update-society.component.html',
  styleUrls: ['./update-society.component.css']
})
export class UpdateSocietyComponent implements OnInit {
  id!: number;
  society!:Society;
  constructor(private societeService:SocieteService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.societeService.getSocietyById(this.id).subscribe(data=>{
      this.society=data;
    },error=>console.log(error) );
  }

  onSubmit() {
    this.societeService.updateSociety(this.id, this.society).subscribe(
      data => {
        this.goToSocietiesist();
      },
      error => console.log(error)
    );
  }

  goToSocietiesist() {
    this.router.navigate(['/societies']);
  }


}
