package com.voting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voting.entity.Candidate;
import com.voting.repository.CandidateRepository;
import com.voting.repository.VoterRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private VoterRepository voterRepository;
    

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // ADD THIS METHOD
    @GetMapping("/admin-login")
    public String adminLoginPage() {
        return "admin-login";
    }

    @PostMapping("/login-admin")
    public String loginAdmin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        if(username.equals("admin")
                && password.equals("1234")) {

            session.setAttribute("admin", username);

            return "redirect:/admin-dashboard";
        }

        return "admin-login";
    }
  

    @GetMapping("/vote-page")
    public String votePage() {

        return "vote-page";
    }

    @GetMapping("/results")
    public String resultPage(Model model) {

        model.addAttribute(
                "candidates",
                candidateRepository.findAll()
        );

        return "results";
    }

    @GetMapping("/add-candidate-page")
    public String addCandidatePage(
            HttpSession session) {

        if(session.getAttribute("admin") == null) {

            return "redirect:/admin-login";
        }

        return "add-candidate";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(
            HttpSession session) {

        if(session.getAttribute("admin") == null) {

            return "redirect:/admin-login";
        }

        return "admin-dashboard";
    }

    @GetMapping("/add-voter-page")
    public String addVoterPage() {
        return "add-voter";
    }
    
    @GetMapping("/candidate-list")
    public String candidateList(Model model) {

        model.addAttribute(
                "candidates",
                candidateRepository.findAll()
        );

        return "candidate-list";
    }
  
    
    
    
    @GetMapping("/voter-list")
    public String voterList(Model model) {

        model.addAttribute(
                "voters",
                voterRepository.findAll()
        );

        return "voter-list";
    }
    
    
    @GetMapping("/winner")
    public String showWinner(Model model) {

        List<Candidate> candidates =
                candidateRepository.findAll();

        if(candidates.isEmpty()) {

            return "redirect:/results";
        }

        Candidate winner = candidates.get(0);

        for(Candidate c : candidates) {

            if(c.getVoteCount() > winner.getVoteCount()) {

                winner = c;
            }
        }

        model.addAttribute("winner", winner);

        return "winner";
    }
    
    
    @GetMapping("/chart")
    public String chartPage(Model model) {

        List<Candidate> candidates =
                candidateRepository.findAll();

        List<String> candidateNames =
                new ArrayList<>();

        List<Integer> voteCounts =
                new ArrayList<>();

        for(Candidate c : candidates) {


            voteCounts.add(c.getVoteCount());
        }

        model.addAttribute(
                "candidateNames",
                candidateNames
        );

        model.addAttribute(
                "voteCounts",
                voteCounts
        );

        return "chart";
    }
    
    
    
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/admin-login";
    }
}