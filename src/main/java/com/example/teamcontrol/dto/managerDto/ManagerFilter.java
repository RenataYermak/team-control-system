package com.example.teamcontrol.dto.managerDto;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ManagerFilter(String lastname,
                            String firstname,
                            LocalDate hireDate) {
}
