import { HttpClient } from '@angular/common/http';
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { User } from '../_model/User';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog'
import { CreateUserComponent } from '../create-user/create-user.component';

//import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

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
  @ViewChild('content')
  modalContent!: TemplateRef<any>;
  constructor(private userService: UserService, private router: Router, private httpClient:HttpClient, private matDialog:MatDialog) {}

  ngOnInit(): void {
    this.getUsers();
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
   this.userService.deleteUser(id).subscribe(data=>{
    console.log(data);
    this.getUsers();
    this.openModalDelete();
   })
  }
  viewUser(id:number)
  {
    this.router.navigate(['users/details',id]);
  }
  addUser() {
    this.router.navigate(['/users/create']);
  }
  openModalDelete() {
   // this.modalService.open(this.modalContent, { centered: true });
  }

  confirmDeleteUser(userId:number) {
    if (confirm("Voulez-vous vraiment supprimer l'utilisateur?")) {
      this.deleteUser(userId);
    }
  }
  openDialog(){
    this.matDialog.open(CreateUserComponent,{
      width : '500px',
    })
  }
}
