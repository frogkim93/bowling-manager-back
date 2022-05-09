package com.frogkim93.bmb.repository.view;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.frogkim93.bmb.model.view.VGameScoreBoard;
import com.frogkim93.bmb.model.view.VMemberPreMonthAvg;

public interface VGameScoreBoardRepository extends JpaRepository<VGameScoreBoard, Integer> {
	List<VGameScoreBoard> findBySeq(int gameSeq);
	
	@Query(value = ""
		+ "select sub.memberSeq, floor((sum(score) / sum(gameCnt))) as 'preMonthAvg' from \r\n"
		+ "(\r\n"
		+ "select \r\n"
		+ "	teammappings.memberSeq, \r\n"
		+ "		(select count(gamescores.score) from gamescores where gamescores.teamMappingSeq = teammappings.seq group by gamescores.teamMappingSeq) as 'gameCnt',\r\n"
		+ "	(select sum(gamescores.score) from gamescores where gamescores.teamMappingSeq = teammappings.seq group by gamescores.teamMappingSeq) as 'score' \r\n"
		+ "from games \r\n"
		+ "inner join teammappings\r\n"
		+ "on games.seq = teammappings.gameSeq\r\n"
		+ "where games.gameDate between \r\n"
		+ "date_sub(date_format(?1, '%Y-%m-01'), interval ?2 month) and\r\n"
		+ "date_sub(date_format(?1, '%Y-%m-01'), interval 1 day)\r\n"
		+ ") as sub\r\n"
		+ "group by sub.memberSeq", nativeQuery = true)
	List<VMemberPreMonthAvg> findByPreMonthAvg(Date month, int monthRange);
}
