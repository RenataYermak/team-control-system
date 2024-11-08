package com.example.teamcontrol.dto.employeeDto;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record EmployeeFilter(String lastname,
                             String firstname,
                             LocalDate hireDate,
                             Long manager) {
}
