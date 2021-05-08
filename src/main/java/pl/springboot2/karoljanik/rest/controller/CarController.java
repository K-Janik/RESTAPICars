package pl.springboot2.karoljanik.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import pl.springboot2.karoljanik.rest.service.CarService;
import pl.springboot2.karoljanik.rest.model.Car;

import java.util.List;

@RequestMapping("/cars")
@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping(path = "{carid}",produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public Car getCarById(@PathVariable("carid") long carId) {
        return carService.getCarById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Part is not in Database"));
    }

    @GetMapping(path = "/color/{color}",produces= {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public List<Car> getCarsByColor(@PathVariable("color") String color) {
        return carService.getCarsByColor(color);
    }

    @PostMapping
    public ResponseEntity insertCar(@NonNull @RequestBody Car car) {
        return new ResponseEntity(carService.insertCar(car),HttpStatus.CREATED);
    }

    @PutMapping(path = "{carid}")
    public void updateCarById(@PathVariable("carid") long carId, @NonNull @RequestBody Car updateCar) {
        carService.updateCarById(carId, updateCar);
    }

    @DeleteMapping(path = "{carid}")
    public ResponseEntity deleteCarById(@PathVariable("carid") long carId) {
        return new ResponseEntity(carService.deleteCarById(carId),HttpStatus.ACCEPTED);
    }
}
