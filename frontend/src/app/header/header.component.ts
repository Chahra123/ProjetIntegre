import { Component, OnInit } from '@angular/core';
import { UserAuthService } from '../_services/user-auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../_services/user.service';
import { User } from 'src/_model/user';
import jwt_decode from 'jwt-decode';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  public loggedInUserName: string | null = null; // Variable to store the logged-in user's name
  public currentUser: User | null = null; // Property to store the logged-in user's information
  constructor(
    private userAuthService: UserAuthService,
    private router: Router,
    public userService:UserService,
    private route:ActivatedRoute
  ) {
    this.route.params.subscribe(params => {
      const userId = params['id'];
      if (userId) {
        this.userService.getUserById(userId).subscribe((user: User) => {
          if (user) {
            this.currentUser = user;
            this.loggedInUserName = `${user.prenom} ${user.nom}`;
          }
        });
      }
    });
  }

  ngOnInit(): void {

    const token = localStorage.getItem('jwtToken');
    console.log('Token:', token);
    if (token) {
      const decodedToken: any = jwt_decode(token);
      const email = decodedToken.sub;
      console.log('Email:', email);

      this.userService.getUserByEmail(email).subscribe(
        data => {
          console.log('User:', data);
          this.loggedInUserName = `${data.prenom} ${data.nom}`;
          console.log('LoggedInUserName:', this.loggedInUserName);
        },
        (error) => {
          console.error('Error fetching user:', error);
        }
      );
    }
  }

  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();

  }
  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/home']);
  }
}
