package com.frogkim93.bmb.model.view;

import java.io.Serializable;

import com.frogkim93.bmb.model.Members;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VGameScoreBoardPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int seq;
	private Members member;
}
