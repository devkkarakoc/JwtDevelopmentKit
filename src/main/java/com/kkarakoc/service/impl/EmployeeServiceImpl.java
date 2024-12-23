package com.kkarakoc.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkarakoc.dto.DtoDepartment;
import com.kkarakoc.dto.DtoEmployee;
import com.kkarakoc.model.Employee;
import com.kkarakoc.repository.EmployeeRepository;
import com.kkarakoc.service.IEmployeeService;


@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public DtoEmployee findEmployeeById(Long id) {
		
			DtoEmployee dtoEmployee = new DtoEmployee(); 
			DtoDepartment dtoDepartment = new DtoDepartment();
         Optional<Employee> dbEmployee=employeeRepository.findById(id);
         
         if(dbEmployee.isPresent()) {
        	 
        	 Employee employee =  dbEmployee.get();
        	 BeanUtils.copyProperties(employee, dtoEmployee);
        	 BeanUtils.copyProperties(employee.getDepartment(), dtoDepartment);
        	 dtoEmployee.setDepartment(dtoDepartment);
        	 
        	 return dtoEmployee;
        	 
         }else {
        	 return null;
         }
         
      
	}

	
	
}
