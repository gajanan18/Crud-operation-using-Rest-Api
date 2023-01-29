package com.consumeapi.restfulapi.controller;

import com.consumeapi.restfulapi.VO.User;
import com.consumeapi.restfulapi.VO.UserBody;
import com.consumeapi.restfulapi.service.ServiceLayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping
@RestController
public class HomeController {


    private final ServiceLayer serviceLayer;

    @Autowired
    public HomeController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping("/")
    public ResponseEntity<?> getData() throws JsonProcessingException {

        return new ResponseEntity<>(serviceLayer.consumeAPI(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getData(@PathVariable String id) throws JsonProcessingException {

        return new ResponseEntity<>(serviceLayer.consumeAPI(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> getData(@RequestBody UserBody userBody) throws JsonProcessingException {

        return new ResponseEntity<>(serviceLayer.consumeAPI(userBody), HttpStatus.OK);
    }
}

