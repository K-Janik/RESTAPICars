package pl.springboot2.karoljanik.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.springboot2.karoljanik.rest.dao.CarDao;
import pl.springboot2.karoljanik.rest.model.Car;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarDao carDao;

    @Autowired
    public CarService(@Qualifier("fakeDao") CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    public Optional<Car> getCarById(long carId) {
        return carDao.getCarById(carId);
    }

    public List<Car> getCarsByColor(String color) {
        return carDao.getCarsByColor(color);
    }

    public int insertCar(Car car) {
        return carDao.insertCar(car);
    }

    public int updateCarById(long carId, Car updateCar) {
        return carDao.updateCarById(carId, updateCar);
    }

    public int deleteCarById(long carId) {
        return carDao.deleteCarById(carId);
    }
}
