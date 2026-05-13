package com.voting.service;

import com.voting.entity.Vote;

public interface VoteService {
	
	Vote saveVote(Vote vote);
	
	boolean hasVoted(int voterId);

}
