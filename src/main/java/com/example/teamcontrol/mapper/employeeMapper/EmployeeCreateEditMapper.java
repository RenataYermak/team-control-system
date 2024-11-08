package com.example.teamcontrol.mapper.employeeMapper;

import com.example.teamcontrol.database.entity.Employee;
import com.example.teamcontrol.database.entity.Manager;
import com.example.teamcontrol.database.repository.ManagerRepository;
import com.example.teamcontrol.dto.employeeDto.EmployeeCreatEditDto;
import com.example.teamcontrol.mapper.Mapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeCreateEditMapper implements Mapper<EmployeeCreatEditDto, Employee> {

    private final ManagerRepository managerRepository;

    @Override
    public Employee map(EmployeeCreatEditDto object) {
        Employee employee = new Employee();
        copy(object, employee);

        return employee;
    }

    @Override
    public Employee map(EmployeeCreatEditDto fromObject, Employee toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(EmployeeCreatEditDto object, Employee Employee) {
        Employee.setFirstname(object.getFirstname());
        Employee.setLastname(object.getLastname());
        Employee.setBirthDate(object.getBirthDate());
        Employee.setHireDate(object.getHireDate());
        Employee.setManager(getManager(object.getManagerId()).get());
    }

    public Optional<Manager> getManager(Long managerId) {
        return Optional.ofNullable(managerId)
                .flatMap(managerRepository::findById);
    }
}
