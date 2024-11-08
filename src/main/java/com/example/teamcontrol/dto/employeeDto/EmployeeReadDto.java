package com.example.teamcontrol.dto.employeeDto;

import com.example.teamcontrol.dto.managerDto.ManagerReadDto;
import java.time.LocalDate;
import lombok.Value;

@Value
public class EmployeeReadDto {

    Long id;
    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
    ManagerReadDto manager;
}
