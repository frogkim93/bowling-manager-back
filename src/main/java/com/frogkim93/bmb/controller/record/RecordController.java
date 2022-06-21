package com.frogkim93.bmb.controller.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frogkim93.bmb.controller.record.service.RecordService;
import com.frogkim93.bmb.dto.record.RecordDto;

@RestController
@RequestMapping(value = "record")
public class RecordController {
	@Autowired
	private RecordService recordService;

	@PostMapping
	private void createRecord(@RequestBody RecordDto record) {
		recordService.createRecord(record);
	}
}
