package com.example.teamcontrol.database.repository;

import com.example.teamcontrol.database.entity.OtherEmployee;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeFilter;
import java.util.List;

public interface OtherEmployeeFilterRepository {

    List<OtherEmployee> findAllByFilter(OtherEmployeeFilter filter);
}
