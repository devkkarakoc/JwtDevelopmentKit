package com.kkarakoc.service;

import com.kkarakoc.dto.DtoEmployee;

public interface IEmployeeService {

	
	DtoEmployee findEmployeeById(Long id);

}
