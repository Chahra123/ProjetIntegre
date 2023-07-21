import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../service/user.service';
import { UserAuthService } from '../service/user-auth.service';
import { User } from '../_model/User';

@Component({
  selector: 'app-token-form',
  templateUrl: './token-form.component.html',
  styleUrls: ['./token-form.component.css']
})
export class TokenFormComponent implements OnInit {
  verificationCode!: string;
  user!:User;
  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private userAuthService:UserAuthService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      const token = params['token'];
      if (token) {
        this.confirmToken(token);
      }
    });
  }

  confirmToken(verificationCode: string) {
    this.userService.confirmToken(verificationCode).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  saveUser()
  {
    this.userService.signup(this.user).subscribe(
      (data) => {
        console.log("DATAAAA:"+data);
      },
      (error) => console.log(error)
    );
  }


  onSubmit() {
    console.log("Verification Code: " + this.verificationCode);
    this.confirmToken(this.verificationCode);
    this.router.navigate(['/login']);
  }

}
