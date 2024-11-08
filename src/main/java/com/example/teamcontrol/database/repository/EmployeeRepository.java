package com.example.teamcontrol.database.repository;

import com.example.teamcontrol.database.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        EmployeeFilterRepository, QuerydslPredicateExecutor<Employee> {
}
