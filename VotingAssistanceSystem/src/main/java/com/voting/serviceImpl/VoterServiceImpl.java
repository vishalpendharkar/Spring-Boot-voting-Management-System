package com.voting.serviceImpl;

 

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entity.Voter;
import com.voting.repository.VoterRepository;
import com.voting.service.VoterService;

@Service
public class VoterServiceImpl implements VoterService{

	@Autowired
	private VoterRepository repository;
	
	@Override
	public List<Voter> getAllVoters(){
		
		return repository.findAll();
	}

	@Override
	public Voter saveVoter(Voter voter) {
		  return repository.save(voter);
		
	}

	@Override
	public Optional<Voter> getVoterByThumbId(String thumbId) {
		// TODO Auto-generated method stub
		 return repository.findByThumbId(thumbId);
	}
	
	@Override
	public void deleteVoter(int id) {

	    repository.deleteById(id);
	}
	
}

