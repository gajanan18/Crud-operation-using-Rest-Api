package com.resttemplate.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.resttemplate.demo.model.Votes;
import com.resttemplate.demo.model.VotesBody;
import com.resttemplate.demo.repository.UserJpa;
import com.resttemplate.demo.service.RestTemplateService;

@RestController
@RequestMapping("/rest")
public class RestTemplateController {
	
	@Autowired
	private UserJpa usersJpa;
	
	@Autowired
	private RestTemplateService restTemplateService;

	@GetMapping("/getAllEmployee")
	public List<Votes> getAllEmployee() throws JsonMappingException, JsonProcessingException {
		
		return restTemplateService.allEmployee();
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getEmpbyId(@PathVariable int id) throws JsonMappingException, JsonProcessingException {
		
		//return restTemplateService.getbyId(id);
		 return new ResponseEntity<>(restTemplateService.getbyId(id), HttpStatus.OK);
	}
	
	@GetMapping("/getById")
	public ResponseEntity<?> getEmpByIdFromReq(@RequestBody VotesBody votesBody) throws JsonMappingException, JsonProcessingException{
		
		return new ResponseEntity<>(restTemplateService.getbyId(votesBody.getId()), HttpStatus.OK);
		
	}
	
	@PostMapping("/addEmployee")
	public ResponseEntity<?> createEmp(@RequestBody VotesBody votesBody) throws Exception{
		
		return new ResponseEntity<>(restTemplateService.createEmployee(votesBody), HttpStatus.OK);
	}	
	
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable(value="id") int id, @RequestBody Votes votes) throws JsonMappingException, JsonProcessingException {
		
		return new ResponseEntity<>(restTemplateService.updateEmployee(id, votes), HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/deleteEmployee/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value="id") int id) throws AttributeNotFoundException
	{
		return restTemplateService.deleteEmp(id);
	}
	
}
