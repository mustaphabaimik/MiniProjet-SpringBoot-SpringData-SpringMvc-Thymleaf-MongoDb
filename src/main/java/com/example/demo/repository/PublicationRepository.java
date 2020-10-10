package com.example.demo.repository;

import java.util.List;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.entity.Publication;

public interface PublicationRepository extends MongoRepository<Publication, String> {
	
	
	
	
	@Query(value = "{'auteurs._id':?0}")
	List<Publication> getpubByAuteur(String id);
	
	@Query(value = "{'categorie':?0}")
	List<Publication> getpubByCat(String categorie);
	

	@Query(value = "{'discipline':?0}")
	List<Publication> getpubBydis(String dis);
	
	@Query(value = "{'Titre':{$regex:?0} , 'categorie' : ?1 , 'discipline' : ?2}")
	List<Publication> rechmulticritere(String titre,String categorie,String discipline);
	
	
	@Query(value = "{'Titre':{$regex:?0}}")
	List<Publication> getpubBytitre(String titre);
	
		

}
