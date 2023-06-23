import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private showLoginSource = new BehaviorSubject<boolean>(true);
  showLogin$ = this.showLoginSource.asObservable();

  setShowLogin(show: boolean) {
    this.showLoginSource.next(show);
  }
}
