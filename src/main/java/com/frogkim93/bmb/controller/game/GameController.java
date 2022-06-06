package com.frogkim93.bmb.controller.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frogkim93.bmb.controller.game.service.GameService;
import com.frogkim93.bmb.dto.game.GameDto;

@RestController
@RequestMapping(value = "game")
public class GameController {
	@Autowired
	private GameService gameService;
	
	@GetMapping(value = "{gameSeq}")
	private GameDto getGame(@PathVariable int gameSeq) {
		return gameService.getGame(gameSeq);
	}
	
	@PostMapping
	private void createGame(@RequestBody GameDto gameInformation) {
		gameService.createGame(gameInformation);
	}
}
