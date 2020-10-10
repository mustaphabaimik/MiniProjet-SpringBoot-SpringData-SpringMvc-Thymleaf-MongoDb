package com.example.demo.controler;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationUpdate.SetValueAppender;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Auteur;

import com.example.demo.entity.Publication;
import com.example.demo.repository.AuteurRepository;
import com.example.demo.repository.PublicationRepository;

@Controller
public class AuteurControler {
	
	@Autowired
	private AuteurRepository AuteurRepo;
	@Autowired
	private PublicationRepository PubRepo;
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	//list auteurs
	@GetMapping("/auteurs") 
	public String showAuteurs(Model model) {
	 
		Set<String> cat=new HashSet<String>();
		for(Publication p:PubRepo.findAll()){
			cat.add(p.getCategorie());
		}
		
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PubRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
		
		Query q2=new Query().with(Sort.by("Nom").ascending());	
		List<Auteur> list=mongotemplate.find(q2, Auteur.class);	
		
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
		model.addAttribute("Auteurs", list); 
		return "auteurList"; 
	}
	
	//list pub d'un auteur
	@GetMapping("/auteurs/publications/{id}") 
	public String showPubsByAuteur(@PathVariable("id") String id,Model model) {
		
		Set<String> cat=new HashSet<String>();
		for(Publication p:PubRepo.findAll()){
			cat.add(p.getCategorie());
		}
		
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PubRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
		
		
		
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
		//model.addAttribute("Auteurs", AuteurRepo.findAll()); 
		model.addAttribute("Publications", PubRepo.getpubByAuteur(id)); 
		return "Accueil";
		
	}
	
	
	
	//trier
	@PostMapping("/auteurs/orderby") 
	public String TrierlisteAuteurs(@RequestParam("selectedoption") String selectedoption,Model model) {
				
		Set<String> cat=new HashSet<String>();
		for(Publication p:PubRepo.findAll()){
			cat.add(p.getCategorie());
		}
		
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PubRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
		
		Query q2=new Query().with(Sort.by(selectedoption).ascending());	
		List<Auteur> list=mongotemplate.find(q2, Auteur.class);	
		
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
		model.addAttribute("Auteurs", list); 
		return "auteurList"; 
		
	}
	
	
	

	

	
	
}
