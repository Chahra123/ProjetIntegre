import { PopupDeleteUserComponent } from './../popup-delete-user/popup-delete-user.component';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { User } from '../_model/User';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog'
import { CreateUserComponent } from '../create-user/create-user.component';
import { UpdateUserComponent } from '../update-user/update-user.component';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  showUsers: boolean = true;
  showRegistration: boolean = false;
  isOpen: boolean[] = [];
  currentUserID!: number;
  isCurrentUser:boolean =false;
  @ViewChild('content')
  modalContent!: TemplateRef<any>;
  loggedInUser!:User;
  constructor(private userService: UserService, private router: Router, private httpClient:HttpClient, private matDialog:MatDialog) {}

  ngOnInit(): void {
    this.getUsers();

    const token = localStorage.getItem('jwtToken');
    console.log('Token:', token);

    if (token) {
      const decodedToken: any = jwt_decode(token);
      const user = decodedToken.sub;
      const userJson = JSON.parse(user);
      console.log('--USER EN JSON:', userJson);
      console.log('--USER EMAIL:', userJson.username);

      this.userService.getUserByEmail(userJson.username).subscribe(
        (data) => {
          console.log('User:', data);
          this.loggedInUser = data;
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );

    }

  }
  private getUsers() {
    this.userService.getUsersList().subscribe((data) => {
      this.users = data;
    });
  }
  updateUser(id: number) {
    this.router.navigate(['users/update', id]);
  }
  deleteUser(id:number)
  {
    if(this.loggedInUser.idUtilisateur==id)
    {
      alert("Vous ne pouvez pas supprimer votre compte");
      this.isCurrentUser=true;
      return;
    }
   this.userService.deleteUser(id).subscribe(data=>{
    console.log(data);
    this.getUsers();
   })
  }
  viewUser(id:number)
  {
    this.router.navigate(['users/details',id]);
  }
  addUser() {
    this.router.navigate(['/users/create']);
  }

  confirmDeleteUser(userId:number) {
    if (confirm("Voulez-vous vraiment supprimer l'utilisateur?")) {
      this.deleteUser(userId);
    }
    this.openDialogDelete();
  }
  openDialog(){
    this.matDialog.open(CreateUserComponent,{
      width : '500px',
    })
  }


  openDialogUpdate(){
    this.matDialog.open(UpdateUserComponent,{
      width : '500px',
    })
  }

  openDialogDelete(){
    this.matDialog.open(PopupDeleteUserComponent,{
      width : '500px',
    })
  }
}
