package com.consumeapi.restfulapi.repository;

import com.consumeapi.restfulapi.VO.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersJpa extends JpaRepository<User,Integer> {
        //
}
