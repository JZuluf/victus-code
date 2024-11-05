package co.edu.uco.victusresidencias.initializer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.uco.victusresidencias.data.dao.impl.postgresql.PostgreSqlDAOFactory;
import co.edu.uco.victusresidencias.entity.CountryEntity;
import co.edu.uco.victusresidencias.initializer.domain.City;
import co.edu.uco.victusresidencias.initializer.repository.CityRepository;

public class CountryController {
	private PostgreSqlDAOFactory repository;
	
	
	@Autowired
	public CountryController(PostgreSqlDAOFactory repository) {
		this.repository = repository;
	}


	@GetMapping("/all")
	public List<CountryEntity> getAll(){
		//repository.commitTransaction()
		return null;//this.PostgreSqlDAOFactory;
	}
	
	@GetMapping("/{id}")
	public City getById(@PathVariable long id) {
		return null;//this.cityRepository.getById(id);
	}
	
	@PostMapping("/new")
	public City saveCity(@RequestBody City city){
		
		return null;//this.cityRepository.save(city);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCity(@PathVariable long id){
		
		//this.cityRepository.delete(id);
	}

}
