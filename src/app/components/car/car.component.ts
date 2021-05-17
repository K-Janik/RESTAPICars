import { Component, OnInit } from '@angular/core';
import {CarClientService, RootObject} from '../services/car-client.service';
import {HttpErrorResponse} from '@angular/common/http';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  messageForUser: string;
  public rootObject: RootObject[];
  public editCar: RootObject;
  public deleteCar: RootObject;

  constructor(private carClientService: CarClientService) { }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.getCars();
  }

  public getCars(): void {
    this.carClientService.getCar().subscribe(
      (response: RootObject[]) => {
        this.rootObject = response;
        console.log(this.rootObject);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  public onAddCar(addForm: NgForm): void {
    document.getElementById('add-car-form').click();
    this.carClientService.insertCar(addForm.value).subscribe(
      (response: RootObject) => {
        console.log(response);
        this.getCars();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateCarById(id: number, car: RootObject): void {
    this.carClientService.updateCarById(id, car).subscribe(
      (response: RootObject) => {
        console.log(response);
        this.getCars();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteCarById(carId: number): void {
    this.carClientService.deleteCarById(carId).subscribe(
      (response: void) => {
        console.log(response);
        this.getCars();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(rootObject: RootObject, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addCarModal');
    }
    if (mode === 'edit') {
      this.editCar = rootObject;
      button.setAttribute('data-target', '#updateCarModal');
    }
    if (mode === 'delete') {
      this.deleteCar = rootObject;
      button.setAttribute('data-target', '#deleteCarModal');
    }
    container.appendChild(button);
    button.click();
  }
}
