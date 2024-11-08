package com.example.teamcontrol.service;

import static com.example.teamcontrol.util.ConstantUtil.ALL_OTHER_EMPLOYEES;
import static com.example.teamcontrol.util.ConstantUtil.OTHER_EMPLOYEE_ID_ONE;
import static com.example.teamcontrol.util.ConstantUtil.OTHER_EMPLOYEE_ID_TWO;
import static com.example.teamcontrol.util.ConstantUtil.POSITION_ID_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.example.teamcontrol.database.entity.OtherEmployee;
import com.example.teamcontrol.database.entity.Position;
import com.example.teamcontrol.database.repository.OtherEmployeeRepository;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeCreatEditDto;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeFilter;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeReadDto;
import com.example.teamcontrol.dto.OtherEmployeeDto.PositionReadDto;
import com.example.teamcontrol.mapper.otherEmployeeMapper.OtherEmployeeCreateEditMapper;
import com.example.teamcontrol.mapper.otherEmployeeMapper.OtherEmployeeReadMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OtherEmployeeServiceTest {

    @Mock
    private OtherEmployeeRepository otherOtherEmployeeRepository;

    @Mock
    private OtherEmployeeReadMapper otherOtherEmployeeReadMapper;

    @Mock
    private OtherEmployeeCreateEditMapper otherOtherEmployeeCreateEditMapper;

    @InjectMocks
    private OtherEmployeeService otherOtherEmployeeService;

    @Test
    void findAll() {
        var otherOtherEmployees = List.of(getOtherEmployee(), getAnotherOtherEmployee());
        var expectedResult = List.of(getOtherEmployeeReadDto(), getAnotherOtherEmployeeReadDto());
        doReturn(otherOtherEmployees).when(otherOtherEmployeeRepository).findAll();
        doReturn(getOtherEmployeeReadDto(), getAnotherOtherEmployeeReadDto()).when(otherOtherEmployeeReadMapper).map(any(OtherEmployee.class));

        var actualResult = otherOtherEmployeeService.findAll();

        assertThat(actualResult).hasSize(ALL_OTHER_EMPLOYEES);
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void findAllWithFilter() {
        var filter = new OtherEmployeeFilter("Lina", "Yermak", LocalDate.of(2007, 11, 23), "1");
        var otherOtherEmployees = List.of(getOtherEmployee(), getAnotherOtherEmployee());
        var expectedResult = List.of(getOtherEmployeeReadDto(), getAnotherOtherEmployeeReadDto());
        doReturn(otherOtherEmployees).when(otherOtherEmployeeRepository).findAllByFilter(filter);
        doReturn(getOtherEmployeeReadDto(), getAnotherOtherEmployeeReadDto()).when(otherOtherEmployeeReadMapper).map(any(OtherEmployee.class));
        var actualResult = otherOtherEmployeeService.findAll(filter);

        assertThat(actualResult).hasSize(2);
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void findById() {
        var otherOtherEmployee = getOtherEmployee();
        var expectedResult = getOtherEmployeeReadDto();
        doReturn(Optional.of(otherOtherEmployee)).when(otherOtherEmployeeRepository).findById(OTHER_EMPLOYEE_ID_ONE);
        doReturn(expectedResult).when(otherOtherEmployeeReadMapper).map(otherOtherEmployee);

        var actualResult = otherOtherEmployeeService.findById(OTHER_EMPLOYEE_ID_ONE);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

    @Test
    void create() {
        var otherOtherEmployeeCreateEditDto = getOtherEmployeeCreateDto();
        var otherOtherEmployee = getOtherEmployee();
        var expectedResult = getOtherEmployeeReadDto();
        doReturn(otherOtherEmployee).when(otherOtherEmployeeCreateEditMapper).map(otherOtherEmployeeCreateEditDto);
        doReturn(otherOtherEmployee).when(otherOtherEmployeeRepository).save(otherOtherEmployee);
        doReturn(expectedResult).when(otherOtherEmployeeReadMapper).map(otherOtherEmployee);

        var actualResult = otherOtherEmployeeService.create(otherOtherEmployeeCreateEditDto);

        assertThat(actualResult.getId()).isNotNull();
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void update() {
        var otherOtherEmployee = getOtherEmployee();
        var otherOtherEmployeeCreateEditDto = getUpdatedOtherEmployeeDto();
        var updatedOtherEmployee = getUpdatedOtherEmployee();
        var expectedResult = getUpdatedOtherEmployeeReadDto();
        doReturn(Optional.of(otherOtherEmployee)).when(otherOtherEmployeeRepository).findById(OTHER_EMPLOYEE_ID_ONE);
        doReturn(updatedOtherEmployee).when(otherOtherEmployeeCreateEditMapper).map(otherOtherEmployeeCreateEditDto, otherOtherEmployee);
        doReturn(updatedOtherEmployee).when(otherOtherEmployeeRepository).saveAndFlush(updatedOtherEmployee);
        doReturn(expectedResult).when(otherOtherEmployeeReadMapper).map(updatedOtherEmployee);

        var actualResult = otherOtherEmployeeService.update(OTHER_EMPLOYEE_ID_ONE, otherOtherEmployeeCreateEditDto);

        assertThat(Optional.of(expectedResult)).isEqualTo(actualResult);
    }

    @Test
    void delete() {
        var otherOtherEmployee = getOtherEmployee();
        doReturn(Optional.of(otherOtherEmployee)).when(otherOtherEmployeeRepository).findById(OTHER_EMPLOYEE_ID_ONE);

        var expectedResult = otherOtherEmployeeService.delete(OTHER_EMPLOYEE_ID_ONE);

        assertTrue(expectedResult);
    }

    private OtherEmployee getOtherEmployee() {
        return OtherEmployee.builder()
                .id(OTHER_EMPLOYEE_ID_ONE)
                .lastname("Lina")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1997, 12, 12))
                .hireDate(LocalDate.of(2007, 11, 23))
                .position(getPosition())
                .build();
    }

    private OtherEmployee getAnotherOtherEmployee() {
        return OtherEmployee.builder()
                .id(OTHER_EMPLOYEE_ID_TWO)
                .lastname("Lina")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1986, 10, 1))
                .hireDate(LocalDate.of(2007, 11, 23))
                .position(getPosition())
                .build();
    }

    private OtherEmployeeReadDto getOtherEmployeeReadDto() {
        return new OtherEmployeeReadDto(
                OTHER_EMPLOYEE_ID_ONE,
                "Lina",
                "Yermak",
                LocalDate.of(1997, 12, 12),
                LocalDate.of(2007, 11, 23),
                getPositionReadDto()
        );
    }

    private OtherEmployeeReadDto getAnotherOtherEmployeeReadDto() {
        return new OtherEmployeeReadDto(
                OTHER_EMPLOYEE_ID_TWO,
                "Lina",
                "Yermak",
                LocalDate.of(1986, 10, 1),
                LocalDate.of(2007, 11, 23),
                getPositionReadDto()
        );
    }

    private OtherEmployeeCreatEditDto getOtherEmployeeCreateDto() {
        return new OtherEmployeeCreatEditDto(
                "Pavel",
                "Zhuk",
                LocalDate.of(1996, 7, 17),
                LocalDate.of(2022, 1, 14),
                7
        );
    }

    private OtherEmployeeCreatEditDto getUpdatedOtherEmployeeDto() {
        return new OtherEmployeeCreatEditDto(
                "Pavel",
                "Zhuk",
                LocalDate.of(1996, 7, 17),
                LocalDate.of(2022, 1, 14),
                7
        );
    }

    private OtherEmployee getUpdatedOtherEmployee() {
        return OtherEmployee.builder()
                .id(OTHER_EMPLOYEE_ID_ONE)
                .lastname("NewName")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1997, 12, 12))
                .hireDate(LocalDate.of(2007, 11, 23))
                .position(getPosition())
                .build();
    }

    private OtherEmployeeReadDto getUpdatedOtherEmployeeReadDto() {
        return new OtherEmployeeReadDto(
                OTHER_EMPLOYEE_ID_ONE,
                "NewName",
                "Yermak",
                LocalDate.of(1997, 12, 12),
                LocalDate.of(2007, 11, 23),
                getPositionReadDto()
        );
    }

    private Position getPosition() {
        return Position.builder()
                .id(POSITION_ID_ONE)
                .name("Administrator")
                .build();
    }

    private PositionReadDto getPositionReadDto() {
        return new PositionReadDto(
                POSITION_ID_ONE,
                "Administrator"
        );
    }
}
