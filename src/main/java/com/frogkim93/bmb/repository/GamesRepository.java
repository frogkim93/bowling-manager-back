package com.frogkim93.bmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frogkim93.bmb.model.Games;

public interface GamesRepository extends JpaRepository<Games, Integer>{}
