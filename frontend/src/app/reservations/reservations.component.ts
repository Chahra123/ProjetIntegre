import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  reservations = [
    {
      id: 1,
      eventName: 'Event 1',
      place: 'Location 1',
      date: 'June 25, 2023',
      numberOfPlaces: 3,
      paymentStatus: 'Paid',
      paymentMethod: 'Credit Card'
    },
    {
      id: 2,
      eventName: 'Event 2',
      place: 'Location 2',
      date: 'July 1, 2023',
      numberOfPlaces: 2,
      paymentStatus: 'Waiting (Not Paid)',
      paymentMethod: 'PayPal'
    },
    {
      id: 3,
      eventName: 'Event 3',
      place: 'Location 3',
      date: 'July 10, 2023',
      numberOfPlaces: 1,
      paymentStatus: 'Paid',
      paymentMethod: 'Cash'
    }
  ];
}