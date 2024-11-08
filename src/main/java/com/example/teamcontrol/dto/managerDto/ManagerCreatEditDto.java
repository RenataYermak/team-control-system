package com.example.teamcontrol.dto.managerDto;

import java.time.LocalDate;
import lombok.Value;

@Value
public class ManagerCreatEditDto {

    String firstname;
    String lastname;
    LocalDate birthDate;
    LocalDate hireDate;
}
