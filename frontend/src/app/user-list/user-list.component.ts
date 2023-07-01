import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { User } from 'src/_model/user';
import { UserService } from '../_services/user.service';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
})
export class UserListComponent implements OnInit {
  users: User[] = [];
  @ViewChild('content')
  modalContent!: TemplateRef<any>;
  constructor(private userService: UserService, private router: Router,private modalService:NgbModal) {}

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
    this.modalService.open(this.modalContent, { centered: true });
  }
}
