package com.vaccine.tracker.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VaccineFees {
	private String vaccine;
	private int fee;
}
