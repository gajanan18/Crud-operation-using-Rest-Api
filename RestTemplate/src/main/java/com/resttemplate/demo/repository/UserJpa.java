package com.resttemplate.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resttemplate.demo.model.Votes;

@Repository
public interface UserJpa extends JpaRepository<Votes, Integer> {

}
