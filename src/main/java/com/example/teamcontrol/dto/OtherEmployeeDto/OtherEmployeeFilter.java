package com.example.teamcontrol.dto.OtherEmployeeDto;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record OtherEmployeeFilter(String lastname,
                                  String firstname,
                                  LocalDate hireDate,
                                  String position) {
}
