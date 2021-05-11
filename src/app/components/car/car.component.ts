import { Component, OnInit } from '@angular/core';
import {CarClientService, RootObject} from '../services/car-client.service';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  messageForUser: string;
  rootObject: RootObject;

  constructor(private carClientService: CarClientService) { }

  ngOnInit(): void {
    this.carClientService.getCar().subscribe(value => {
      this.rootObject = value;
    });
  }

  // tslint:disable-next-line:typedef
  sayHello(value: string) {
    this.messageForUser = 'Cześć ' + value;
  }
}
