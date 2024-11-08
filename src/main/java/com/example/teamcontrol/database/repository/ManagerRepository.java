package com.example.teamcontrol.database.repository;

import com.example.teamcontrol.database.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ManagerRepository extends JpaRepository<Manager, Long>,
        ManagerFilterRepository, QuerydslPredicateExecutor<Manager> {
}
