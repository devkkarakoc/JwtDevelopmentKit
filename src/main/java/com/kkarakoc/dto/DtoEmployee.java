package com.kkarakoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoEmployee {

	
	private Long id;
	private String firstname;
	private String lastname;
	
	private DtoDepartment department;
}
