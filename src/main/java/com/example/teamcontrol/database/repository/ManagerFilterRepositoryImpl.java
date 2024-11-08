package com.example.teamcontrol.database.repository;

import static com.example.teamcontrol.database.entity.QManager.manager;

import com.example.teamcontrol.database.entity.Manager;
import com.example.teamcontrol.database.querydsl.QPredicates;
import com.example.teamcontrol.dto.managerDto.ManagerFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ManagerFilterRepositoryImpl implements ManagerFilterRepository {

    private final EntityManager entityManager;

    @Override
    public List<Manager> findAllByFilter(ManagerFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.lastname(), manager.lastname::containsIgnoreCase)
                .add(filter.firstname(), manager.firstname::containsIgnoreCase)
                .add(filter.hireDate(), manager.hireDate::eq)
                .build();

        return new JPAQuery<Manager>(entityManager)
                .select(manager)
                .from(manager)
                .where(predicate)
                .fetch();
    }
}
