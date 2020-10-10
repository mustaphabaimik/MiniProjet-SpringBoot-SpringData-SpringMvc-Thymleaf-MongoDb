package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.repository.AuteurRepository;

@Component
public class AuteurConverter implements Converter<String, Auteur> {

	@Autowired
	private AuteurRepository auteurrepo;
	
	@Override
	public Auteur convert(String id) {
		// TODO Auto-generated method stub
		Auteur auteur = auteurrepo.findById(id).get();	
		return auteur;
	}

}
