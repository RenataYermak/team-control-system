package com.example.teamcontrol.dto.employeeDto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class EmployeeCreatEditDto {

    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
    Long managerId;
}
