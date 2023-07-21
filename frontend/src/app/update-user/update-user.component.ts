import { Component, OnInit } from '@angular/core';
import { User } from '../_model/User';
import { UserService } from '../service/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog'
import { PopupUpdateUserComponent } from '../popup-update-user/popup-update-user.component';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id!: number;
  user: User = new User();

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute, private matDialog:MatDialog) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.userService.getUserById(this.id).subscribe(data=>{
      this.user=data;
    },error=>console.log(error) );
  }

  onSubmit() {
    this.userService.updateUser(this.id, this.user).subscribe(
      data => {
        this.goToUsersList();
        this.confirmUpdate();

      },
      error => console.log(error)
    );

  }

  goToUsersList() {
    this.router.navigate(['/users']);
  }

  confirmUpdate(){
    this.matDialog.open(PopupUpdateUserComponent,{
      width : '500px',
    })
  }

}
