package com.example.teamcontrol.dto.OtherEmployeeDto;

import java.time.LocalDate;
import lombok.Value;

@Value
public class OtherEmployeeCreatEditDto {

    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
    Integer positionId;
}
