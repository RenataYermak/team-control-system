package com.example.teamcontrol.database.repository;

import com.example.teamcontrol.database.entity.OtherEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherEmployeeRepository extends JpaRepository<OtherEmployee, Long>,
        OtherEmployeeFilterRepository, QuerydslPredicateExecutor<OtherEmployee> {
}
