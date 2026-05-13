package com.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.entity.Candidate;

@Repository
public interface CandidateRepository  extends JpaRepository<Candidate, Integer>{

}
