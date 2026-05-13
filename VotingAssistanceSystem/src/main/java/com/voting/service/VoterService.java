package com.voting.service;

import java.util.List;
import java.util.Optional;

import com.voting.entity.Voter;

public interface VoterService {

	List<Voter> getAllVoters();
	
	Voter saveVoter(Voter voter);
	
	Optional<Voter> getVoterByThumbId(String thumbId);
	
	void deleteVoter(int id);
}
