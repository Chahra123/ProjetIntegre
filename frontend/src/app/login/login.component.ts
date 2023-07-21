import { Component, OnInit} from '@angular/core';
import { UserService } from '../service/user.service';
import { UserAuthService } from '../service/user-auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog'
import { PopupTokenSecurityComponent } from '../popup-token-security/popup-token-security.component';
import { PopupWarningEmailComponent } from '../popup-warning-email/popup-warning-email.component';
import { PopupResetPasswordComponent } from '../popup-reset-password/popup-reset-password.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  showLogin: boolean = true;
  user: any = {};
  signupForm: any;
  emailExistsError:boolean = false;

  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router:Router, private matDialog:MatDialog
  ) {}


  ngOnInit(): void {
    this.userAuthService.showLogin$.subscribe((showLogin) => {
      this.showLogin = showLogin;
    });
    this.showLogin = true;
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
    this.userService.checkEmailExists(this.user.email).subscribe((exists) => {
      console.log("Does it exist:"+exists);
      if (exists) {
        this.emailExistsError = true;
        this.openDialogWarningEmail();
      }
      else
      {
        this.userService.signup(this.user).subscribe(
          (data) => {
            console.log("DATAAAA:"+data);

          },
          (error) => console.log(error)
        );
        this.router.navigate(['/tokenform']);
        this.openDialogToken();
      }
    });
  }

  forgotPassword(loginForm: NgForm) {
    // Get the email from the form
    const email = loginForm.value.userName;
    this.openDialogResetPwd()


    this.userService.forgotPassword(email).subscribe(
      (response: any) => {
        console.log('Password reset email sent*************:', response);


      },
      (error) => {
        // Handle error or show an error message to the user
        console.error('Error sending password reset email:', error);
      }
    );
  }


  openDialogToken(){
    this.matDialog.open(PopupTokenSecurityComponent,{
      width : '500px',
    })
  }

  openDialogWarningEmail()
  {
    this.matDialog.open(PopupWarningEmailComponent,{
      width : '500px',
    })
  }

  openDialogResetPwd()
  {
    this.matDialog.open(PopupResetPasswordComponent,{
      width : '500px',
    })
  }

}
