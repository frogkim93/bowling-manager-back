package com.frogkim93.bmb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "teams")
public class Teams {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date regTime;
	
	@OneToMany(targetEntity = TeamMappings.class)
	@JoinColumn(name = "teamSeq")
	List<TeamMappings> teamMappingList;
}
