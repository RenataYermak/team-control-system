package com.example.teamcontrol.database.repository;

import com.example.teamcontrol.database.entity.Employee;
import com.example.teamcontrol.dto.employeeDto.EmployeeFilter;
import java.util.List;

public interface EmployeeFilterRepository {

    List<Employee> findAllByFilter(EmployeeFilter filter);
}
