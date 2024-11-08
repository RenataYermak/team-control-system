package com.example.teamcontrol.dto.managerDto;

import java.time.LocalDate;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
public class ManagerReadDto {

    Long id;
    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
}
