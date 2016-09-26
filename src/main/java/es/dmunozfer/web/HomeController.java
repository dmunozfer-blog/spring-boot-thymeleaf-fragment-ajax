package es.dmunozfer.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.dmunozfer.service.Car;
import es.dmunozfer.service.CarService;

@Controller
@RequestMapping
public class HomeController {

	private CarService carService;

	@Autowired
	public HomeController(CarService carService) {
		this.carService = carService;
	}

	@RequestMapping("/")
	public String index(Model model) {
		List<String> brands = carService.findAllBrands();
		Car car = Car.builder().build();
		model.addAttribute("car", car);
		model.addAttribute("brands", brands);
		return "home";
	}

	@RequestMapping("/ajax/brands")
	public String ajaxBrands(@RequestParam("brand") String brand, Model model) {
		List<String> models = carService.findAllModelsByBrand(brand);
		model.addAttribute("models", models);
		return "home :: models";
	}
}
