package com.voting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting.entity.Vote;
import com.voting.entity.Voter;

public interface VoteRepository  extends JpaRepository<Vote, Integer>{

	Optional<Vote> findByVoter(Voter voter);
}
