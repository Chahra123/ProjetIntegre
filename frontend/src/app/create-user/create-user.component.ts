import { PopUpCreateUserComponent } from './../pop-up-create-user/pop-up-create-user.component';
import { Router } from '@angular/router';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { User } from '../_model/User';
import { Role } from '../_model/Role';
import { UserService } from '../service/user.service';
import { MatDialog } from '@angular/material/dialog'



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
  constructor(private userService: UserService, private router:Router,private matDialog:MatDialog) {}

  ngOnInit(): void {}
  onSubmit() {
    console.log(this.user);
    this.saveUser();
    this.openDialog();
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

  openDialog(){
    this.matDialog.open(PopUpCreateUserComponent,{
      width : '500px',
    })
  }
}
