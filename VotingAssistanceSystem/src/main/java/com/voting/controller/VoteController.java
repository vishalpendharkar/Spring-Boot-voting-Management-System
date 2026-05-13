package com.voting.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voting.entity.Candidate;
import com.voting.entity.Vote;
import com.voting.entity.Voter;
import com.voting.repository.CandidateRepository;
import com.voting.repository.VoterRepository;
import com.voting.service.VoteService;

import org.springframework.ui.Model;

@Controller
public class VoteController {

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private CandidateRepository candidateRepository;
    
    
    @Autowired
    private VoteService voteService;

    @PostMapping("/vote")
    public String castVote(
            @RequestParam String thumbId,
            @RequestParam int candidateId) {

        Optional<Voter> voterOptional =
                voterRepository.findByThumbId(thumbId);

        if(voterOptional.isEmpty()) {

            return "redirect:/vote-page";
        }

        Voter voter = voterOptional.get();

        // Prevent duplicate voting
        if(voter.isVoted()) {

            return "redirect:/vote-page";
        }

        Optional<Candidate> candidateOptional =
                candidateRepository.findById(candidateId);

        if(candidateOptional.isEmpty()) {

            return "redirect:/vote-page";
        }

        Candidate candidate =
                candidateOptional.get();

        // Increase vote count
        candidate.setVoteCount(
                candidate.getVoteCount() + 1
        );

        // Mark voter as voted
        voter.setVoted(true);

        candidateRepository.save(candidate);

        voterRepository.save(voter);

        return "redirect:/results";
    }
    
    
    
    
    
    @GetMapping("/{voterId}")
    public String showVotingPage(@PathVariable int voterId, Model model) {

        model.addAttribute("candidates", candidateRepository.findAll());
        model.addAttribute("voterId", voterId);

        return "vote";
    }

    @PostMapping("/submit")
    public String submitVote(@RequestParam int voterId,
                             @RequestParam int candidateId,
                             Model model) {

        if(voteService.hasVoted(voterId)) {

            model.addAttribute("message", "You have already voted!");
            return "result";
        }

        Voter voter = voterRepository.findById(voterId).orElse(null);
        Candidate candidate = candidateRepository.findById(candidateId).orElse(null);

        Vote vote = new Vote(voter, candidate);

        voteService.saveVote(vote);

        model.addAttribute("message", "Vote submitted successfully!");

        return "result";
    }
    
    @GetMapping("/vote/{id}")
    public String votePage(@PathVariable int id) {

        System.out.println("Vote Page Opened : " + id);

        return "vote";
    }
    
}