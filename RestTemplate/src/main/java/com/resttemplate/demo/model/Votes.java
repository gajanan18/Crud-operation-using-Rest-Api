package com.resttemplate.demo.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="votes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Votes {
	
	@Id
    private Integer id;
    private String image_id;
	private String sub_id;	
	private String created_at;	
	private Integer value;	
	private String country_code;
	
    public Votes(Integer id, String image_id, String sub_id, String created_at, Integer value, String country_code) {
		super();
		this.id = id;
		this.image_id = image_id;
		this.sub_id = sub_id;
		this.created_at = created_at;
		this.value = value;
		this.country_code = country_code;
	}
    
    public Votes() {}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	
	

}
