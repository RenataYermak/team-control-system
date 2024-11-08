package com.example.teamcontrol.dto.employeeDto;

import java.time.LocalDate;
import lombok.Value;

@Value
public class EmployeeCreatEditDto {

    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
    Long managerId;
}
