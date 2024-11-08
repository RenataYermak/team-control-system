package com.example.teamcontrol.service;

import static com.example.teamcontrol.util.ConstantUtil.ALL_EMPLOYEES;
import static com.example.teamcontrol.util.ConstantUtil.EMPLOYEE_ID_ONE;
import static com.example.teamcontrol.util.ConstantUtil.EMPLOYEE_ID_TWO;
import static com.example.teamcontrol.util.ConstantUtil.MANAGER_ID_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.example.teamcontrol.database.entity.Employee;
import com.example.teamcontrol.database.entity.Manager;
import com.example.teamcontrol.database.repository.EmployeeRepository;
import com.example.teamcontrol.dto.employeeDto.EmployeeCreatEditDto;
import com.example.teamcontrol.dto.employeeDto.EmployeeFilter;
import com.example.teamcontrol.dto.employeeDto.EmployeeReadDto;
import com.example.teamcontrol.dto.managerDto.ManagerReadDto;
import com.example.teamcontrol.mapper.employeeMapper.EmployeeCreateEditMapper;
import com.example.teamcontrol.mapper.employeeMapper.EmployeeReadMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeReadMapper employeeReadMapper;

    @Mock
    private EmployeeCreateEditMapper employeeCreateEditMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void findAll() {
        var employees = List.of(getEmployee(), getAnotherEmployee());
        var expectedResult = List.of(getEmployeeReadDto(), getAnotherEmployeeReadDto());
        doReturn(employees).when(employeeRepository).findAll();
        doReturn(getEmployeeReadDto(), getAnotherEmployeeReadDto()).when(employeeReadMapper).map(any(Employee.class));

        var actualResult = employeeService.findAll();

        assertThat(actualResult).hasSize(ALL_EMPLOYEES);
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void findAllWithFilter() {
        var filter = new EmployeeFilter("Lina", "Yermak", LocalDate.of(2007, 11, 23), 1L);
        var employees = List.of(getEmployee(), getAnotherEmployee());
        var expectedResult = List.of(getEmployeeReadDto(), getAnotherEmployeeReadDto());
        doReturn(employees).when(employeeRepository).findAllByFilter(filter);
        doReturn(getEmployeeReadDto(), getAnotherEmployeeReadDto()).when(employeeReadMapper).map(any(Employee.class));
        var actualResult = employeeService.findAll(filter);

        assertThat(actualResult).hasSize(2);
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void findById() {
        var employee = getEmployee();
        var expectedResult = getEmployeeReadDto();
        doReturn(Optional.of(employee)).when(employeeRepository).findById(EMPLOYEE_ID_ONE);
        doReturn(expectedResult).when(employeeReadMapper).map(employee);

        var actualResult = employeeService.findById(EMPLOYEE_ID_ONE);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

    @Test
    void create() {
        var employeeCreateEditDto = getEmployeeCreateDto();
        var employee = getEmployee();
        var expectedResult = getEmployeeReadDto();
        doReturn(employee).when(employeeCreateEditMapper).map(employeeCreateEditDto);
        doReturn(employee).when(employeeRepository).save(employee);
        doReturn(expectedResult).when(employeeReadMapper).map(employee);

        var actualResult = employeeService.create(employeeCreateEditDto);

        assertThat(actualResult.getId()).isNotNull();
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void update() {
        var employee = getEmployee();
        var employeeCreateEditDto = getUpdatedEmployeeDto();
        var updatedEmployee = getUpdatedEmployee();
        var expectedResult = getUpdatedEmployeeReadDto();
        doReturn(Optional.of(employee)).when(employeeRepository).findById(EMPLOYEE_ID_ONE);
        doReturn(updatedEmployee).when(employeeCreateEditMapper).map(employeeCreateEditDto, employee);
        doReturn(updatedEmployee).when(employeeRepository).saveAndFlush(updatedEmployee);
        doReturn(expectedResult).when(employeeReadMapper).map(updatedEmployee);

        var actualResult = employeeService.update(EMPLOYEE_ID_ONE, employeeCreateEditDto);

        assertThat(Optional.of(expectedResult)).isEqualTo(actualResult);
    }

    @Test
    void delete() {
        var employee = getEmployee();
        doReturn(Optional.of(employee)).when(employeeRepository).findById(EMPLOYEE_ID_ONE);

        var expectedResult = employeeService.delete(EMPLOYEE_ID_ONE);

        assertTrue(expectedResult);
    }

    private Employee getEmployee() {
        return Employee.builder()
                .id(EMPLOYEE_ID_ONE)
                .lastname("Lina")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1997, 12, 12))
                .hireDate(LocalDate.of(2007, 11, 23))
                .manager(getManager())
                .build();
    }

    private Employee getAnotherEmployee() {
        return Employee.builder()
                .id(EMPLOYEE_ID_TWO)
                .lastname("Lina")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1986, 10, 1))
                .hireDate(LocalDate.of(2007, 11, 23))
                .manager(getManager())
                .build();
    }

    private EmployeeReadDto getEmployeeReadDto() {
        return new EmployeeReadDto(
                EMPLOYEE_ID_ONE,
                "Lina",
                "Yermak",
                LocalDate.of(1997, 12, 12),
                LocalDate.of(2007, 11, 23),
                getManagerReadDto()
        );
    }

    private EmployeeReadDto getAnotherEmployeeReadDto() {
        return new EmployeeReadDto(
                EMPLOYEE_ID_TWO,
                "Lina",
                "Yermak",
                LocalDate.of(1986, 10, 1),
                LocalDate.of(2007, 11, 23),
                getManagerReadDto()
        );
    }

    private EmployeeCreatEditDto getEmployeeCreateDto() {
        return new EmployeeCreatEditDto(
                "Pavel",
                "Zhuk",
                LocalDate.of(1996, 7, 17),
                LocalDate.of(2022, 1, 14),
                7L
        );
    }

    private EmployeeCreatEditDto getUpdatedEmployeeDto() {
        return new EmployeeCreatEditDto(
                "Pavel",
                "Zhuk",
                LocalDate.of(1996, 7, 17),
                LocalDate.of(2022, 1, 14),
                7L
        );
    }

    private Employee getUpdatedEmployee() {
        return Employee.builder()
                .id(EMPLOYEE_ID_ONE)
                .lastname("NewName")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1997, 12, 12))
                .hireDate(LocalDate.of(2007, 11, 23))
                .manager(getManager())
                .build();
    }

    private EmployeeReadDto getUpdatedEmployeeReadDto() {
        return new EmployeeReadDto(
                EMPLOYEE_ID_ONE,
                "NewName",
                "Yermak",
                LocalDate.of(1997, 12, 12),
                LocalDate.of(2007, 11, 23),
                getManagerReadDto()
        );
    }

    private Manager getManager() {
        return Manager.builder()
                .id(MANAGER_ID_ONE)
                .lastname("Renata")
                .firstname("Shturo")
                .birthDate(LocalDate.of(1997, 12, 12))
                .hireDate(LocalDate.of(2012, 9, 12))
                .build();
    }

    private ManagerReadDto getManagerReadDto() {
        return new ManagerReadDto(
                MANAGER_ID_ONE,
                "Renata",
                "Shturo",
                LocalDate.of(1997, 12, 12),
                LocalDate.of(2012, 9, 12)
        );
    }
}
