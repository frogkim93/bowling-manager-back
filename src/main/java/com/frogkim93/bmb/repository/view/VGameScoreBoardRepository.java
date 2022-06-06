package com.frogkim93.bmb.repository.view;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.frogkim93.bmb.model.view.VGameScoreBoard;
import com.frogkim93.bmb.model.view.VMemberPreMonthAvg;

public interface VGameScoreBoardRepository extends JpaRepository<VGameScoreBoard, Integer> {
	List<VGameScoreBoard> findBySeq(int gameSeq);
}
