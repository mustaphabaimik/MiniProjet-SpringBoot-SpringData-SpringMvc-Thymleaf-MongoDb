package com.example.demo.controler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Auteur;
import com.example.demo.entity.Publication;
import com.example.demo.repository.AuteurRepository;
import com.example.demo.repository.PublicationRepository;

@Controller
public class PublicationControler {

	@Autowired
	private PublicationRepository PublicationRepo;
	
	@Autowired
	private AuteurRepository AuteurRepo;
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	
	//page accueil
	@GetMapping("/") 
	public String showAccueil(Model model) {		
		Set<String> cat=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			cat.add(p.getCategorie());
		}
		
		
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
			
		model.addAttribute("Publications", PublicationRepo.findAll()); 
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
	
		return "Accueil"; 
	}
	

	
	
	//afficher detail d'une publication
	@GetMapping("/publications/detailpub/{id}") 
	public String showPublicationdetail(@PathVariable("id") String id, Model model) {
		
		Set<String> cat=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			cat.add(p.getCategorie());
		}
		
		
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
		
		Publication p=PublicationRepo.findById(id).get();
		model.addAttribute("publication",p); 
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
		return "detail-pub"; 
		
		
		
		 
	}
	
	//recherche par categorie
	@GetMapping("/publications/searchBycat/{searchTerm}") 
	public String searchPublicationsByCat(@PathVariable("searchTerm") String searchTerm,Model model) {
		
		
		
		Set<String> cat=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			cat.add(p.getCategorie());
		}
				
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
		
		
		model.addAttribute("Publications", PublicationRepo.getpubByCat(searchTerm)); 
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
		return "Accueil"; 
		
	}
	
	//recherche par organisme
	@GetMapping("/publications/searchByorg/{searchTerm}") 
	public String searchPublicationsbyorg(@PathVariable("searchTerm") String searchTerm,Model model) {	
		
		Set<String> cat=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			cat.add(p.getCategorie());
		}
		
		
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
		
		
		Query q2=new Query().addCriteria(Criteria.where("Organisme").is(searchTerm));
		List<Auteur> list=mongotemplate.find(q2, Auteur.class);	
		
		
		Set<Publication> pubs=new HashSet<Publication>();
		for(Auteur a:list) {
			Query q=new Query().addCriteria(Criteria.where("auteurs._id").is(a.getId()));
			for(Publication p:mongotemplate.find(q, Publication.class)) {
				pubs.add(p);
			}		
		}
		
		model.addAttribute("Publications",pubs); 
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
		return "Accueil";
		
	}
	
	//recherche par discipline
	@GetMapping("/publications/searchBydis/{searchTerm}") 
	public String searchPublicationsbydis(@PathVariable("searchTerm") String searchTerm,Model model) {
			
		Set<String> cat=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			cat.add(p.getCategorie());
		}
		
		
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
		
		
		model.addAttribute("Publications", PublicationRepo.getpubBydis(searchTerm)); 
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
		return "Accueil"; 
		
	}
	
	//recherche multicritere
	@PostMapping("/publications/rechmulticritere") 
	public String rechmulticritere(@RequestParam Map<String, String> requestparams,Model model) {
		
		
		
		Set<String> cat=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			cat.add(p.getCategorie());
		}
		
		
		Set<String> discipline=new HashSet<String>();
		for(Publication p:PublicationRepo.findAll()){
			discipline.add(p.getDiscipline());
		}
		
		Set<String> organisme=new HashSet<String>();
		for(Auteur aut:AuteurRepo.findAll()){
			organisme.add(aut.getOrganisme());
		}
		
		
		model.addAttribute("Publications", PublicationRepo.rechmulticritere(requestparams.get("titre"),requestparams.get("categorie"),requestparams.get("discipline"))); 
		model.addAttribute("Categories", cat); 
		model.addAttribute("organismes", organisme);
		model.addAttribute("disciplines", discipline);
		return "Accueil"; 
		
	}
	
	
	
	
	//recherche par titre
		@PostMapping("/publications/rechpartitre") 
		public String searchPublicationsBytitle(@RequestParam("titre") String titre,Model model) {
					
			Set<String> cat=new HashSet<String>();
			for(Publication p:PublicationRepo.findAll()){
				cat.add(p.getCategorie());
			}
					
			Set<String> discipline=new HashSet<String>();
			for(Publication p:PublicationRepo.findAll()){
				discipline.add(p.getDiscipline());
			}
			
			Set<String> organisme=new HashSet<String>();
			for(Auteur aut:AuteurRepo.findAll()){
				organisme.add(aut.getOrganisme());
			}
			
			
			model.addAttribute("Publications", PublicationRepo.getpubBytitre(titre)); 
			model.addAttribute("Categories", cat); 
			model.addAttribute("organismes", organisme);
			model.addAttribute("disciplines", discipline);
			return "Accueil"; 
			
		}
		
		
		
		
	
}
