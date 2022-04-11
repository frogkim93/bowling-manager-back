package com.frogkim93.bmb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.Members;

public interface MembersRepository extends JpaRepository<Members, Integer> {
	List<Members> findByMaster(Accounts master);
	Members deleteBySeq(int seq);
}
