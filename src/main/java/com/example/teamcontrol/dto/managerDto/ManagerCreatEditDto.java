package com.example.teamcontrol.dto.managerDto;

import java.time.LocalDate;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class ManagerCreatEditDto {

    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
}
