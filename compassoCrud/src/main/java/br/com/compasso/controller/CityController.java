package br.com.compasso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compasso.data.v1.vo.CityVO;
import br.com.compasso.services.CityServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "City Endpoint") 
@RestController
@RequestMapping("/api/v1/city")
public class CityController {
	
	@Autowired
	private CityServices service;
	
	@ApiOperation(value = "Find all citys" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<CityVO> findAll() {
		return service.findAll();
	}	
	
	@ApiOperation(value = "Find a specific city by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public CityVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}	
	
	@ApiOperation(value = "Find a specific city by your name" )
	@GetMapping(value = "name/{name}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public CityVO findByName(@PathVariable("name") String name) {
		return service.findByName(name);
	}	
	
	@ApiOperation(value = "Find a specific city by your state" )
	@GetMapping(value = "state/{state}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<CityVO> findByState(@PathVariable("state") String state) {
		return service.findByState(state);
	}	
	
	@ApiOperation(value = "Create a new city")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public CityVO create(@RequestBody CityVO city) {
		return service.create(city);
	}
	
	@ApiOperation(value = "Update a specific city")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public CityVO update(@RequestBody CityVO city) {
		return service.update(city);
	}	
	
	@ApiOperation(value = "Delete a specific city by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<CityServices> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}