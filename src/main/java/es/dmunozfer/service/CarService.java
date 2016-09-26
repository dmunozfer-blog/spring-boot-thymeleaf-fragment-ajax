package es.dmunozfer.service;

import java.util.List;

public interface CarService {

	List<String> findAllBrands();
	List<String> findAllModelsByBrand(String brand);
}
