package pl.springboot2.karoljanik.rest.dao;

import pl.springboot2.karoljanik.rest.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    List<Car> getAllCars();

    Optional<Car> getCarById(long carId);

    List<Car> getCarsByColor(String color);

    int insertCar(Car car);

    int updateCarById(long carId, Car car);

    int deleteCarById(long carId);

}
