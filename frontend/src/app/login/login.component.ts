import { Component, OnInit} from '@angular/core';
import { UserService } from '../service/user.service';
import { UserAuthService } from '../service/user-auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

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
    private router:Router
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
      if (exists) {
        this.emailExistsError = true; // Définir la variable à true si l'e-mail existe déjà
      } else {
        // L'e-mail n'existe pas, procéder à l'enregistrement de l'utilisateur
        this.userService.signup(this.user).subscribe(
          (data) => {
            console.log(data);
            // Rediriger vers la page de tokenform ou effectuer d'autres actions
            this.router.navigate(['/tokenform']);
          },
          (error) => console.log(error)
        );
      }
    });
  }

  forgotPassword(loginForm: NgForm) {
    // Get the email from the form
    const email = loginForm.value.userName;

    this.userService.forgotPassword(email).subscribe(
      (response: any) => {
        // Handle success or show a notification to the user
        console.log('Password reset email sent:', response);
        this.router.navigate(['users/reset-password']);
        this.openModalMail();
      },
      (error) => {
        // Handle error or show an error message to the user
        console.error('Error sending password reset email:', error);
      }
    );
  }
  openModalMail() {
    //this.modalService.open(this.modalContent, { centered: true });
  }

}
