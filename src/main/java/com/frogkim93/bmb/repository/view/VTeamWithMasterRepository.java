package com.frogkim93.bmb.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.view.VTeamWithMaster;

public interface VTeamWithMasterRepository extends JpaRepository<VTeamWithMaster, Integer> {
	List<VTeamWithMaster> findByMasterOrderByTeamIndexAsc(Accounts master);
}
