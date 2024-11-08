package com.example.teamcontrol.database.repository;

import static com.example.teamcontrol.database.entity.QOtherEmployee.otherEmployee;
import static com.example.teamcontrol.database.entity.QPosition.position;

import com.example.teamcontrol.database.entity.OtherEmployee;
import com.example.teamcontrol.database.querydsl.QPredicates;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeFilter;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OtherEmployeeFilterRepositoryImpl implements OtherEmployeeFilterRepository {

    private final EntityManager entityManager;

    @Override
    public List<OtherEmployee> findAllByFilter(OtherEmployeeFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.lastname(), otherEmployee.lastname::containsIgnoreCase)
                .add(filter.firstname(), otherEmployee.firstname::containsIgnoreCase)
                .add(filter.hireDate(), otherEmployee.hireDate::eq)
                .add(filter.position(), otherEmployee.position.name::containsIgnoreCase)
                .build();

        return new JPAQuery<OtherEmployee>(entityManager)
                .select(otherEmployee)
                .from(otherEmployee)
                .join(otherEmployee.position, position)
                .where(predicate)
                .fetch();
    }
}
