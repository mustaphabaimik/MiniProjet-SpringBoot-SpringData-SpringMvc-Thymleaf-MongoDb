package com.example.demo.controler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Auteur;
import com.example.demo.entity.Publication;
import com.example.demo.repository.AuteurRepository;
import com.example.demo.repository.PublicationRepository;

@Controller
public class AdminControler {
	
	@Autowired
	private AuteurRepository AuteurRepo;
	@Autowired
	private PublicationRepository PubRepo;
	
	
	
	
	//afficher dashboard admin
	@GetMapping("/admin") 
	public String showAdminaccueil(Model model) {
		model.addAttribute("Publications", PubRepo.findAll()); 
		return "Dashboard"; 
	}
	
	//list des auteurs
	@GetMapping("/admin/auteurs") 
	public String showAuteurList(Model model) {
		model.addAttribute("Auteurs", AuteurRepo.findAll()); 
		return "auteurListDashboard"; 
	}
	

	//formulaire d'ajout dun auteur
	@GetMapping("/admin/addauteurform")
	 public String addForm(Auteur auteur) { 
		 return "add-auteur"; 
	 }
	
	//formulaire d'ajout dune publication
	 @GetMapping("/admin/addpubform")
	 public String addPubform(Publication publication,Model model) { 
		 model.addAttribute("Auteurs", AuteurRepo.findAll());
		 return "add-publication"; 
	 }
	 
	 
	 //formulaire d'ajout d'un mot cle a une publication
	 @GetMapping("/admin/addmotcletopub/{id}")
	 public String addmotcletopubform(@PathVariable("id") String id,Model model) {	 
		 Publication publication=new Publication();
		 publication.setId(id);
		 model.addAttribute("publication", publication); 	
		 return "add-motcle"; 
	 }
	 
	 //ajouter mot cle a une pub
	 @PostMapping("/admin/addmotcletoMypub") 
	 public String addmotcle(Publication publication) {
		
		Publication p=PubRepo.findById(publication.getId()).get();
		List<String> list=publication.getMotcles();
		for(String s:list) {
			p.getMotcles().add(s);
		}
		PubRepo.save(p);
		return "add-motcle"; 
	} 
	
	 //ajouter un auteur
	 @PostMapping("/admin/addauteur") 
	 public String addAuteur(Auteur auteur, Model model) {
		 AuteurRepo.save(auteur);
		 model.addAttribute("Auteurs", AuteurRepo.findAll());
		 
		 return "auteurListDashboard"; 
	} 
	 
	 //ajouter une pub
	 @PostMapping("/admin/addpub") 
	 public String addPub(Publication publication, Model model) {
		publication.setDatePub(new Date());	 
		 for(Auteur a:publication.getAuteurs()) {
			 a.setNbr(a.getNbr()+1);
			AuteurRepo.save(a);
		 }
		 PubRepo.save(publication);	
		model.addAttribute("Publications", PubRepo.findAll()); 
		 return "Dashboard"; 
	} 
	 
	 //suppression dun auteur
	 @GetMapping("/admin/delete/{id}") 
	 public String deleteAuteur(@PathVariable("id") String id, Model model) { 
		 Auteur auteur = AuteurRepo.findById(id).get();	
		AuteurRepo.delete(auteur);  
		 model.addAttribute("Auteurs", AuteurRepo.findAll()); 
		 return "auteurListDashboard";  
	} 
	 
	 //suppression dune pub
	 @GetMapping("/admin/deletepub/{id}") 
	 public String deletepub(@PathVariable("id") String id,Model model) { 			
			Publication p=PubRepo.findById(id).get();
			for(Auteur a:p.getAuteurs()) {
				a.setNbr(a.getNbr()-1);
				AuteurRepo.save(a);
			}
			PubRepo.delete(p);
			model.addAttribute("Publications", PubRepo.findAll()); 
			return "Dashboard";		 
	} 
	 
	 //formulaire de modification dun auteur
	 @GetMapping("/admin/edit/{id}")
	 public String showUpdateForm(@PathVariable("id") String id, Model model) {
		 Auteur a = AuteurRepo.findById(id).get(); 
		 model.addAttribute("auteur", a); 
		 return "update-auteur"; 
		
	} 
	 
	//formulaire de modification dune pub
	 @GetMapping("/admin/editpub/{id}")
	 public String showUpdatepubForm(@PathVariable("id") String id, Model model) {
		 Publication p=PubRepo.findById(id).get();
		 model.addAttribute("publication", p); 
		 return "update-pub"; 
	} 
	 
	 //modification dun auteur
	 @PostMapping("/admin/update/{id}") 
	 public String updateAuteur(@PathVariable("id") String id, Auteur auteur, Model model) { 	 
	          AuteurRepo.save(auteur);
			  model.addAttribute("Auteurs", AuteurRepo.findAll()); 
			  return "auteurListDashboard";  
	  } 
	 
	//modification dune pub
	 @PostMapping("/admin/updatepub/{id}") 
	 public String updatePub(@PathVariable("id") String id, Publication publication, Model model) { 	 
		      Publication p=PubRepo.findById(publication.getId()).get();
		      p.setTitre(publication.getTitre());
		      p.setResume(publication.getResume());
		      p.setCategorie(publication.getCategorie());
		      p.setDiscipline(publication.getDiscipline());      
	          PubRepo.save(p);
			  model.addAttribute("Publications", PubRepo.findAll()); 		  
			  return "Dashboard";  
	  } 
	 
	 
	
	 
	 

}
