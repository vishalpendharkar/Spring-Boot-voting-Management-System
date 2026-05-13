package com.voting.service;

import java.util.List;

import com.voting.entity.Candidate;

public interface CandidateService {

	 Candidate addCandidate(Candidate candidate);

	    List<Candidate> getAllCandidates();
	    
	    void deleteCandidate(int id);
	    
	    Candidate getCandidateById(int id);
	    
	    Candidate saveCandidate(Candidate candidate);
	    
	    Candidate updateCandidate(Candidate candidate);
	    
	    
}

