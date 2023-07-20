import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import jwt_decode from 'jwt-decode';


@Injectable({
  providedIn: 'root',
})
export class UserAuthService {
  private showLoginSource = new BehaviorSubject<boolean>(true);
  showLogin$ = this.showLoginSource.asObservable();
  constructor() {}
  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }
  public getRoles(): string[] | null {
    const rolesString = localStorage.getItem('roles');
    if (rolesString === null) {
      return null;
    }
    return JSON.parse(rolesString);
  }
  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', jwtToken);
  }
  public getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }
  public clear()
  {
    localStorage.clear();
  }
  public isLoggedIn()
  {
    return this.getToken() && this.getRoles();
  }
}
