package com.example.teamcontrol.database.repository;

import com.example.teamcontrol.database.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {
}
