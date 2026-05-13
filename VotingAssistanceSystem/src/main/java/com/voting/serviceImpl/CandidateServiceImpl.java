package com.voting.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entity.Candidate;
import com.voting.repository.CandidateRepository;
import com.voting.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Candidate addCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
    
    @Override
    public void deleteCandidate(int id) {

        candidateRepository.deleteById(id);
    }

    @Override
    public Candidate getCandidateById(int id) {

        return candidateRepository.findById(id).get();
    }

	@Override
	public Candidate saveCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
	 return candidateRepository.save(candidate);
	}

	@Override
	public Candidate updateCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		return candidateRepository.save(candidate);
	}
}