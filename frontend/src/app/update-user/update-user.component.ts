import { User } from 'src/_model/user';
import { UserService } from './../_services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'console';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {
  id!: number;
  user: User = new User();

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

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
      },
      error => console.log(error)
    );
  }

  goToUsersList() {
    this.router.navigate(['/users']);
  }
}
