package com.example.teamcontrol.database.repository;

import static com.example.teamcontrol.database.entity.QEmployee.employee;
import static com.example.teamcontrol.database.entity.QManager.manager;

import com.example.teamcontrol.database.entity.Employee;
import com.example.teamcontrol.database.querydsl.QPredicates;
import com.example.teamcontrol.dto.employeeDto.EmployeeFilter;
import com.querydsl.jpa.impl.JPAQuery;

import jakarta.persistence.EntityManager;
import java.util.List;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmployeeFilterRepositoryImpl implements EmployeeFilterRepository {

    private final EntityManager entityManager;

    @Override
    public List<Employee> findAllByFilter(EmployeeFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.lastname(), employee.lastname::containsIgnoreCase)
                .add(filter.firstname(), employee.firstname::containsIgnoreCase)
                .add(filter.hireDate(), employee.hireDate::eq)
                .add(filter.manager(), employee.manager.id::eq)
                .build();

        return new JPAQuery<Employee>(entityManager)
                .select(employee)
                .from(employee)
                .join(employee.manager, manager)
                .where(predicate)
                .fetch();
    }
}
