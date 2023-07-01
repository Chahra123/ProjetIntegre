import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { User } from 'src/_model/user';
import { UserService } from '../_services/user.service';
import { Role } from 'src/_model/role';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css'],
})
export class CreateUserComponent implements OnInit {
  user: User = new User();
  roles:Role[]=[
    {'idRole':1,'nomRole':'ADMIN'},
    {'idRole':2,'nomRole':'CLIENT'},
    {'idRole':3,'nomRole':'ORGANISATEUR'}
  ];
  selectedRoles: number[] = [];
  @ViewChild('content')
  modalContent!: TemplateRef<any>;
  constructor(private userService: UserService, private router:Router, private modalService:NgbModal) {}

  ngOnInit(): void {}
  onSubmit() {
    console.log(this.user);
    this.saveUser();
    this.openModalCreate();
  }
  saveUser() {
    this.userService.createUser(this.user).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(['/users']);
      },
      (error) => console.log(error)
    );
  }
  goToUserList()
  {
    this.router.navigate(['/users']);
  }
  openModalCreate() {
    this.modalService.open(this.modalContent, { centered: true });
  }
}
