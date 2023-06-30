import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../_services/user.service';
import { UserAuthService } from '../_services/user-auth.service';
import { Router } from '@angular/router';
import { User } from 'src/_model/user';
import { LoginRequest } from 'src/_model/LoginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  showLogin: boolean = true;
  user: any = {};
  signupForm: any;
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router:Router
  ) {}


  ngOnInit(): void {
    this.userAuthService.showLogin$.subscribe((showLogin) => {
      this.showLogin = showLogin;
    });
  }

  login(loginForm: NgForm) {
    this.userService.login(loginForm.value).subscribe(
      (response:any) => {
        this.userAuthService.setRoles(response.user.roles);
        this.userAuthService.setToken(response.jwtToken);
        console.log(response);
        const role = response.user.roles[0].nomRole;
        if(role == 'ADMIN')
        {
          this.router.navigate(['/dashboard']);
        }
        else if (role == 'CLIENT'){
          this.router.navigate(['/home']);
        }
        else if (role == 'ORGANISATEUR')
        {
          this.router.navigate(['/home']);
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }
saveUser1() {
    this.userService.signup(this.user).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => console.log(error)
    );

    this.router.navigate(['/tokenform']);
  }

  forgotPassword(loginForm: NgForm) {
    // Get the email from the form
    const email = loginForm.value.userName;

    this.userService.forgotPassword(email).subscribe(
      (response: any) => {
        // Handle success or show a notification to the user
        console.log('Password reset email sent:', response);
        this.router.navigate(['users/reset-password']);
      },
      (error) => {
        // Handle error or show an error message to the user
        console.error('Error sending password reset email:', error);
      }
    );
  }


}
