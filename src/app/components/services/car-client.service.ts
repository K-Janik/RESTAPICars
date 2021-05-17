import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {root} from 'rxjs/internal-compatibility';

@Injectable({
  providedIn: 'root'
})
export class CarClientService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private httpClient: HttpClient) {
  }

  public getCar(): Observable<RootObject[]> {
    return this.httpClient.get<RootObject[]>(`${this.apiServerUrl}/cars`);
  }
  public insertCar(rootObject: RootObject): Observable<RootObject> {
    return this.httpClient.post<RootObject>(`${this.apiServerUrl}/cars`, rootObject);
  }
  public updateCarById(carId: number, rootObject: RootObject): Observable<RootObject> {
    return this.httpClient.put<RootObject>(`${this.apiServerUrl}/cars/${carId}`, rootObject);
  }
  public deleteCarById(carId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiServerUrl}/cars/${carId}`);
  }
}

export interface RootObject{
  carId: number;
  brand: string;
  model: string;
  color: string;
}



