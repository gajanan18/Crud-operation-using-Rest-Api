package com.consumeapi.restfulapi.VO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    private int id;
    private String image_id;
    private String sub_id;
    private String created_at;
    private int value;
    private String country_code;

    public User(){}

    public User(int id, String image_id, String sub_id, String created_at, int value, String country_code) {
        this.id = id;
        this.image_id = image_id;
        this.sub_id = sub_id;
        this.created_at = created_at;
        this.value = value;
        this.country_code = country_code;
    }

}
