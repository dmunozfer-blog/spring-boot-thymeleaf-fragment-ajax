package es.dmunozfer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

	private final String[] brands = { "Audi", "BMW", "Citroën", "Ford", "Mercedes", "Reanult", "Seat" };
	private List<Car> cars;

	public CarServiceImpl() {
		this.cars = new ArrayList<>();
		for (String brand : brands) {
			for (int i = 0; i < 5; i++) {
				this.cars.add(generateRandomCar(brand));
			}
		}
	}

	@Override
	public List<String> findAllBrands() {
		return Arrays.asList(brands);
	}

	@Override
	public List<String> findAllModelsByBrand(String brand) {
		List<String> result = cars.stream().filter(x -> brand.equals(x.getBrand())).map(Car::getModel).collect(Collectors.toList());
		result.sort(java.util.Comparator.naturalOrder());
		return result;
	}

	private Car generateRandomCar(String brand) {
		return Car.builder().brand(brand).model(String.format("Modelo %s %d", brand, ThreadLocalRandom.current().nextInt(1, 100))).build();
	}

}
