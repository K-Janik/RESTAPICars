package pl.springboot2.karoljanik.rest.dao;

import org.springframework.stereotype.Repository;
import pl.springboot2.karoljanik.rest.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class CarRepository implements CarDao {

    private static List<Car> carList = new ArrayList<>();

    public CarRepository() {
        carList.add(new Car(1L, "Mercedes", "GLC 300", "Blue"));
        carList.add(new Car(2L, "Maserati", "Quatroporte", "Black"));
        carList.add(new Car(3L, "Alfa Romeo", "Giulia", "Red"));
    }

    @Override
    public List<Car> getAllCars() {
        return carList;
    }

    @Override
    public Optional<Car> getCarById(long carId) {

        return carList.stream()
                .filter(car -> car.getCarId() == carId)
                .findFirst();
    }

    @Override
    public List<Car> getCarsByColor(String color) {

        return carList.stream()
                .filter(car -> car.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    @Override
    public int insertCar(Car car) {
        carList.add(new Car(car.getCarId(), car.getBrand(), car.getModel(), car.getColor()));
        return 1;
    }

    @Override
    public int updateCarById(long carId, Car updateCar) {

        return getCarById(carId)
                .map(car -> {
                    int indexOfPartToUpdate = carList.indexOf(car);
                    if (indexOfPartToUpdate >= 0) {
                        carList.set(indexOfPartToUpdate, new Car(carId, updateCar.getBrand(), updateCar.getModel(), updateCar.getColor()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int deleteCarById(long carId) {
        Optional<Car> carMaybe = getCarById(carId);
        if (carMaybe.isEmpty()) {
            return 0;
        }
        carList.remove(carMaybe.get());
        return 1;


    }
}
