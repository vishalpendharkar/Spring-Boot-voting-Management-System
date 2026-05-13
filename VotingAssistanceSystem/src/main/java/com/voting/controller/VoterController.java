package com.voting.controller;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
 
import org.springframework.web.bind.annotation.RequestMapping;

import com.voting.entity.Voter;
import com.voting.service.VoterService;

 


@Controller
@RequestMapping("/voter")
public class VoterController {

	@Autowired
	private VoterService service;
	
	
	@GetMapping("/")
	public String home() {
		
		return "index";
	}
	
	@PostMapping("/add")
	public String addVoterForm(Voter voter) {

	    service.saveVoter(voter);

	    return "redirect:/admin-dashboard";
	}
	  
	  @PostMapping("/saveVoter")
	  public String saveVoter(@ModelAttribute("voter") Voter voter) {

	      service.saveVoter(voter);

	      return "redirect:/addVoter";
	  }
	  
	  @GetMapping("/all")
	    public List<Voter> getAllVoters() {
	        return service.getAllVoters();
	    }
	  
	  @GetMapping("/{thumbId}")
	    public Voter getVoter(@PathVariable String thumbId) {
	        return service.getVoterByThumbId(thumbId)
	                .orElseThrow(() -> new RuntimeException("Voter not found"));
	    }
	  
	  
	  @GetMapping("/delete/{id}")
	    public String deleteVoter(@PathVariable int id) {

	        service.deleteVoter(id);

	        return "redirect:/voter-list";
	    }
	   
}
	  

	  
