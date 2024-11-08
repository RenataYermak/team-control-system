package com.example.teamcontrol.database.repository;

import com.example.teamcontrol.database.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

public interface ManagerRepository extends JpaRepository<Manager, Long>,
        ManagerFilterRepository, QuerydslPredicateExecutor<Manager> {
}
