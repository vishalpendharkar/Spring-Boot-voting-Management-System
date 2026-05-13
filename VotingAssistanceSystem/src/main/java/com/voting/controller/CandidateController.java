package com.voting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.voting.entity.Candidate;
import com.voting.service.CandidateService;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @GetMapping
    public String listCandidates(Model model) {
        model.addAttribute("candidates", service.getAllCandidates());
        return "candidates";
    }

    @GetMapping("/new")
    public String createCandidateForm(Model model) {

        Candidate candidate = new Candidate();
        model.addAttribute("candidate", candidate);

        return "create_candidate";
    }

    @PostMapping
    public String saveCandidate(@ModelAttribute("candidate") Candidate candidate) {

        service.saveCandidate(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/edit/{id}")
    public String editCandidateForm(@PathVariable int id, Model model) {

        model.addAttribute("candidate", service.getCandidateById(id));
        return "edit_candidate";
    }

    @PostMapping("/{id}")
    public String updateCandidate(@PathVariable int id,
                                  @ModelAttribute("candidate") Candidate candidate) {

        Candidate existingCandidate =
                service.getCandidateById(id);

        existingCandidate.setName(
                candidate.getName()
        );

        existingCandidate.setParty(
                candidate.getParty()
        );

        existingCandidate.setVoteCount(
                candidate.getVoteCount()
        );

        service.updateCandidate(existingCandidate);

        return "redirect:/candidates";
    }

    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable int id) {

    	service.deleteCandidate(id);

        return "redirect:/candidates";
    }
}