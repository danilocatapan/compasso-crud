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

import br.com.compasso.data.model.Client;
import br.com.compasso.data.v1.vo.ClientVO;
import br.com.compasso.services.ClientServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Client Endpoint") 
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
	
	@Autowired
	private ClientServices service;
	
	@ApiOperation(value = "Find all clientsVO" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<ClientVO> findAllVO() {
		return service.findAllVO();
	}	
	
	@ApiOperation(value = "Find all clients" )
	@GetMapping(value = "findAll/", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<Client> findAll() {
		return service.findAll();
	}	
	
	@ApiOperation(value = "Find a specific clientVO by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO findByIdVO(@PathVariable("id") Long id) {
		return service.findByIdVO(id);
	}	
	
	@ApiOperation(value = "Find a specific client by your ID" )
	@GetMapping(value = "myFindById/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public Client myFindById(@PathVariable("id") Long id) {
		return service.myFindById(id);
	}	
	
	@ApiOperation(value = "Find a specific client by your name" )
	@GetMapping(value = "name/{name}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<ClientVO> findByName(@PathVariable("name") String name) {
		return service.findByName(name);
	}	
	
	@ApiOperation(value = "Create a new client")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO createVO(@RequestBody ClientVO client) {
		return service.createVO(client);
	}
	
	@ApiOperation(value = "Update a specific client")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO update(@RequestBody ClientVO client) {
		return service.update(client);
	}	
	
	@ApiOperation(value = "Delete a specific client by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<ClientServices> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
	@ApiOperation(value = "Delete a specific client by your ID")
	@DeleteMapping("deleteById/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
	}	
	
}