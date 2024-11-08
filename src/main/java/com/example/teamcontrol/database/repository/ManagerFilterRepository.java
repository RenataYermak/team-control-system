package com.example.teamcontrol.database.repository;

import com.example.teamcontrol.database.entity.Manager;
import com.example.teamcontrol.dto.managerDto.ManagerFilter;
import java.util.List;

public interface ManagerFilterRepository {

    List<Manager> findAllByFilter(ManagerFilter filter);
}
