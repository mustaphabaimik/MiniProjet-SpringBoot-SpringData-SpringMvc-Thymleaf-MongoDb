package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.entity.Auteur;
import com.example.demo.entity.Publication;

public interface AuteurRepository extends MongoRepository<Auteur, String> {

		
	/*@Query(value = "{'Organisme':?0}",fields = "{'Nom':0,'Prenom':0,'Adresse':0,'Grade':0,'Prenom':0}")
	List<Auteur> getAuteurParOrg(String org);*/
}
