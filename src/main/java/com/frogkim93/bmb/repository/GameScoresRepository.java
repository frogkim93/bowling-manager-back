package com.frogkim93.bmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frogkim93.bmb.model.GameScores;

public interface GameScoresRepository extends JpaRepository<GameScores, Integer>{}
