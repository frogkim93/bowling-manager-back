package com.frogkim93.bmb.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.frogkim93.bmb.model.Accounts;
import com.frogkim93.bmb.model.Members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "v_memberwithlastavg")
public class VMemberWithLastAvg {
	@Id
	@Column(nullable = false)
	private int seq;
	
	@OneToOne(targetEntity = Members.class)
	@JoinColumn(name = "seq")
	private Members member;
	
	@ManyToOne(targetEntity = Accounts.class)
	@JoinColumn(name = "masterSeq")
	private Accounts account;
	
	@Column(nullable = false)
	private Double lastAvg;
}
