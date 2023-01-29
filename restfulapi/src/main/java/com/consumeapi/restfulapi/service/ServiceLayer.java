package com.consumeapi.restfulapi.service;


import com.consumeapi.restfulapi.VO.User;

import com.consumeapi.restfulapi.VO.UserBody;
import com.consumeapi.restfulapi.repository.UsersJpa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ServiceLayer {

    @Autowired
    public ServiceLayer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private final RestTemplate restTemplate;

    @Autowired
    private UsersJpa usersJpa;

    public List<User> consumeAPI() throws JsonProcessingException {
        String Url = "https://api.thecatapi.com/v1/votes/" ;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key","17d94b92-754f-46eb-99a0-65be65b5d18f");
        HttpEntity entity = new HttpEntity<>(headers);

        String output= restTemplate.exchange(Url,HttpMethod.GET,entity,String.class).getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = objectMapper.readValue(output, new TypeReference<List<User>>(){});
        return userList;
    }

    public User consumeAPI(String id) throws JsonProcessingException {
        String Url = "https://api.thecatapi.com/v1/votes/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key","17d94b92-754f-46eb-99a0-65be65b5d18f");
        HttpEntity entity = new HttpEntity<>(headers);

        String output= restTemplate.exchange(Url,HttpMethod.GET,entity,String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        User userList = objectMapper.readValue(output, User.class);
        return userList;
    }

    public User consumeAPI(UserBody userBody) throws JsonProcessingException {
        String Url = "https://api.thecatapi.com/v1/votes/" + userBody.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key","17d94b92-754f-46eb-99a0-65be65b5d18f");
        HttpEntity entity = new HttpEntity<>(headers);

        String output= restTemplate.exchange(Url,HttpMethod.GET,entity,String.class).getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        User userList = objectMapper.readValue(output, User.class);
        usersJpa.save(userList);
        return userList;
    }

}