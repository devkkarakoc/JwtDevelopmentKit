package com.kkarakoc.controller;

import com.kkarakoc.dto.DtoEmployee;

public interface IRestEmployeeController {

	public DtoEmployee findEmployeeById(Long id);
}
