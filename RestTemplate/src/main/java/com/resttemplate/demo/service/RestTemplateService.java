package com.resttemplate.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resttemplate.demo.exception.ResourceNotFoundException;
import com.resttemplate.demo.model.Votes;
import com.resttemplate.demo.model.VotesBody;
import com.resttemplate.demo.repository.UserJpa;

@Service
public class RestTemplateService { 
	
	
	private static final RestTemplateService restTemplateService = null;

	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private UserJpa usersJpa;

	// method to get all data

	public List<Votes> allEmployee() throws JsonParseException, JsonMappingException, JsonProcessingException{
		
		String Url = "https://api.thecatapi.com/v1/votes/";
		RestTemplate restTemplate =  new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", "");
		HttpEntity entity = new HttpEntity(headers);
		
		String response = restTemplate.exchange(Url,HttpMethod.GET, entity ,String.class).getBody();
		//System.out.println(response);
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<Votes> voteResult = objectMapper.readValue(response, new TypeReference<List<Votes>>(){});
		return voteResult;
		
	} 
	
	// method to get data by id
	
	
	public Votes getbyId(@PathVariable(value="id") int id) throws JsonMappingException, JsonProcessingException {
		
		String Url = "https://api.thecatapi.com/v1/votes/" + id;
		RestTemplate restTemplate =  new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", "");
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		
		String response = restTemplate.exchange(Url,HttpMethod.GET, entity ,String.class).getBody();
		//System.out.println(response);
		ObjectMapper objectMapper = new ObjectMapper();
		Votes voteResult = objectMapper.readValue(response,Votes.class);
		usersJpa.save(voteResult);
		return voteResult;
		
	}
	
	//method to get data by id from post request body
	
	public Votes consumeAPI(VotesBody votesBody) throws JsonMappingException, JsonProcessingException {
		String Url = "https://api.thecatapi.com/v1/votes/" + votesBody.getId();
		 HttpHeaders headers = new HttpHeaders();
	     headers.set("x-api-key","");
	     HttpEntity entity = new HttpEntity<>(headers);

	     String output= restTemplate.exchange(Url,HttpMethod.GET,entity,String.class).getBody();
	     ObjectMapper objectMapper = new ObjectMapper();
	     Votes userList = objectMapper.readValue(output, Votes.class);
	     usersJpa.save(userList);
	     return userList;
			
	}

	// method to update the user
	
	public Votes updateEmployee(int id, Votes votes) throws JsonMappingException, JsonProcessingException {
		String Url = "https://api.thecatapi.com/v1/votes/" + id;
		 HttpHeaders headers = new HttpHeaders();
	     headers.set("x-api-key","");
	     HttpEntity entity = new HttpEntity<>(headers);

	     String output= restTemplate.exchange(Url,HttpMethod.GET,entity,String.class).getBody();
	     ObjectMapper objectMapper = new ObjectMapper();
	     Votes userList = objectMapper.readValue(output, Votes.class);
	     
	     userList.setImage_id(votes.getImage_id());
	     userList.setSub_id(votes.getSub_id());
	     userList.setCountry_code(votes.getCountry_code());
	     userList.setCreated_at(votes.getCreated_at());
	     userList.setValue(votes.getValue());
	     
	     usersJpa.save(userList);
	     
	     return userList;
	}

	
	// method to create a new user

	public ResponseEntity<?> createEmployee(VotesBody votesBody) throws JsonMappingException, JsonProcessingException {
		
	/*	String Url = "https://api.thecatapi.com/v1/votes/" + id;
		 HttpHeaders headers = new HttpHeaders();
	     headers.set("x-api-key","");
	     HttpEntity entity = new HttpEntity<>(headers);

	     String output= restTemplate.exchange(Url,HttpMethod.GET,entity,String.class).getBody();
	     ObjectMapper objectMapper = new ObjectMapper();
	     Votes userList = objectMapper.readValue(output,Votes.class);
	     
	      //List<Votes> listOfUser = userList.stream().filter(checkUser -> checkUser.getId()==id).collect((Collectors.toList()));
	      usersJpa.save(userList);
	      return userList; */
		
		
		String Url = "https://api.thecatapi.com/v1/votes/";
		 HttpHeaders headers = new HttpHeaders();
	     headers.set("x-api-key","");
	     HttpEntity entity = new HttpEntity<>(headers);
	     
	     String output= restTemplate.exchange(Url,HttpMethod.GET,entity,String.class).getBody();
	     ObjectMapper objectMapper = new ObjectMapper();
		
	     List<Votes> userList = objectMapper.readValue(output,new TypeReference<List<Votes>>() {});
	     
	     int id = votesBody.getId();
			
			 Votes existingUser = usersJpa.findById(id).orElse(null);
		     
		     if (existingUser != null) {
		    	 return new ResponseEntity<>(existingUser, HttpStatus.FOUND);
		     }
		     
		     for(Votes votes: userList) {
		    	 if(votes.getId() == id) {
		    		 existingUser = votes;
		    	 }
		     }
		     
		     if(existingUser == null) {
		    	 return new ResponseEntity<>("User doesn't exists", HttpStatus.NOT_FOUND);
		     } else {
		    	  usersJpa.save(existingUser);
		    	  return new ResponseEntity<>("new User data created", HttpStatus.CREATED);
		     }
		     
	     
	}
	
	// method to delete user
	
	public Map<String, Boolean> deleteEmp(@PathVariable(value = "id") int id) throws AttributeNotFoundException
	{
	    
	    Optional<Votes> user = usersJpa.findById(id);
	    
	    if (user.isPresent()) {
	    	usersJpa.deleteById(id);
			Map<String, Boolean> response = new HashMap<>();
		    response.put("deleted", Boolean.TRUE);
		    return response;
	    } else {
	    	Map<String, Boolean> response = new HashMap<>();
		    response.put("User not found for this id: " + id, Boolean.FALSE);
		    return response;
	    } 
	    
	}
	
	
	
}
