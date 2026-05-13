package com.voting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.entity.Voter;

@Repository
public interface VoterRepository
        extends JpaRepository<Voter, Integer> {

    Optional<Voter> findByThumbId(String thumbId);
}