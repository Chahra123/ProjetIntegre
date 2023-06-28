import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
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
  constructor(private userService: UserService, private router:Router) {}

  ngOnInit(): void {}
  onSubmit() {
    console.log(this.user);
    this.saveUser();
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
}
