import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Society } from '../_model/Society';
@Injectable({
  providedIn: 'root'
})
export class SocieteService {
  PATH_OF_API = 'http://localhost:8754';
  requestHeader = new HttpHeaders({
    'No-Auth': 'True',
  });

  constructor(
    private httpclient: HttpClient  ) {}
  private showSocieteSource = new BehaviorSubject<boolean>(true);
  showSociete$ = this.showSocieteSource.asObservable();

  private showRegistrationSource = new BehaviorSubject<boolean>(true);
  showRegistration$ = this.showRegistrationSource.asObservable();

  setShowSociete(show: boolean) {
    this.showSocieteSource.next(show);
  }

  getSocietiesList():Observable<Society[]>{
    return this.httpclient.get<Society[]>(this.PATH_OF_API+'/societies');
  }
  createSociety(user:Society):Observable<Object>{
    return this.httpclient.post(this.PATH_OF_API+'/societies',user);
  }
  updateSociety(id:number,user:Society):Observable<Object>
  {
    return this.httpclient.put(this.PATH_OF_API+'/societies'+`/${id}`,user);
  }
  getSocietyById(id:number):Observable<Society>
  {
    return this.httpclient.get<Society>(this.PATH_OF_API+'/societies'+`/${id}`);
  }
  deleteSociety(id:number):Observable<Object>
  {
    return this.httpclient.delete(this.PATH_OF_API+'/societies'+`/${id}`);
  }
}
