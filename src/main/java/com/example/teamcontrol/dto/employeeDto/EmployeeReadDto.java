package com.example.teamcontrol.dto.employeeDto;

import com.example.teamcontrol.database.entity.Manager;
import com.example.teamcontrol.dto.managerDto.ManagerReadDto;
import java.time.LocalDate;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class EmployeeReadDto {

    Long id;
    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
    ManagerReadDto manager;
}
