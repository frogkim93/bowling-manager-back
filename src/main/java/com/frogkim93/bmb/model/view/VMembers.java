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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "v_members")
public class VMembers {
	@Id
	private int seq;
	
	@ManyToOne(targetEntity = Accounts.class)
	@JoinColumn(name = "masterSeq")
	private Accounts account;
	
	@OneToOne(targetEntity = Members.class)
	@JoinColumn(name = "seq")
	private Members member;
	
	@Column
	private Double avg;
}
