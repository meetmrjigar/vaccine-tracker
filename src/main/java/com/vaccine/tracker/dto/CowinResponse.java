package com.vaccine.tracker.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CowinResponse{
	@SuppressWarnings("unused")
	private ArrayList<CowinResponseSessionContent> sessions;	
}

