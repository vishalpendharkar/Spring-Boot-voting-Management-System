package com.voting.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.entity.Vote;
import com.voting.entity.Voter;
import com.voting.repository.VoteRepository;
import com.voting.repository.VoterRepository;
import com.voting.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private  VoteRepository voteRepository;
    @Autowired
    private VoterRepository voterRepository;

    @Override
    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

    @Override
    public boolean hasVoted(int voterId) {

        Voter voter = voterRepository.findById(voterId).orElse(null);

        if(voter == null) {
            return false;
        }

        Optional<Vote> vote = voteRepository.findByVoter(voter);

        return vote.isPresent();
    }
}