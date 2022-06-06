package com.frogkim93.bmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frogkim93.bmb.model.Teams;

public interface TeamsRepository extends JpaRepository<Teams, Integer>{
	Teams findTop1ByOrderByRegTime();
}
