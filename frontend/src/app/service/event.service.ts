import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evenement } from '../_model/Evenement';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private PATH_OF_API = 'http://localhost:8754/events'; // Replace with your back-end API base URL.

  constructor(private http: HttpClient) { }

  getEventsForSociety(societyId: number): Observable<Evenement[]> {
    const url = `${this.PATH_OF_API}/society/${societyId}`;
    return this.http.get<Evenement[]>(url);
  }
}
