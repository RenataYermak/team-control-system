package com.example.teamcontrol.dto.OtherEmployeeDto;

import java.time.LocalDate;
import lombok.Value;

@Value
public class OtherEmployeeReadDto {

    Long id;
    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
    PositionReadDto position;
}
