package com.example.teamcontrol.mapper.employeeMapper;

import com.example.teamcontrol.database.entity.Employee;
import com.example.teamcontrol.dto.employeeDto.EmployeeReadDto;
import com.example.teamcontrol.mapper.Mapper;
import com.example.teamcontrol.mapper.managerMapper.ManagerReadMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeReadMapper implements Mapper<Employee, EmployeeReadDto> {

    private final ManagerReadMapper managerReadMapper;

    @Override
    public EmployeeReadDto map(Employee object) {
        var manager = Optional.ofNullable(object.getManager())
                .map(managerReadMapper::map)
                .orElse(null);
        return new EmployeeReadDto(
                object.getId(),
                object.getFirstname(),
                object.getLastname(),
                object.getBirthDate(),
                object.getHireDate(),
                manager
        );
    }
}
