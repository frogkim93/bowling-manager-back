package com.frogkim93.bmb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frogkim93.bmb.model.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
	Accounts findByUserId(String userId);
}
