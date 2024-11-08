package com.example.teamcontrol.service;

import static com.example.teamcontrol.util.ConstantUtil.ALL_MANAGERS;
import static com.example.teamcontrol.util.ConstantUtil.MANAGER_ID_ONE;
import static com.example.teamcontrol.util.ConstantUtil.MANAGER_ID_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import com.example.teamcontrol.database.entity.Manager;
import com.example.teamcontrol.database.repository.ManagerRepository;
import com.example.teamcontrol.dto.managerDto.ManagerCreatEditDto;
import com.example.teamcontrol.dto.managerDto.ManagerFilter;
import com.example.teamcontrol.dto.managerDto.ManagerReadDto;
import com.example.teamcontrol.mapper.managerMapper.ManagerCreateEditMapper;
import com.example.teamcontrol.mapper.managerMapper.ManagerReadMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private ManagerReadMapper managerReadMapper;

    @Mock
    private ManagerCreateEditMapper managerCreateEditMapper;

    @InjectMocks
    private ManagerService managerService;

    @Test
    void findAll() {
        var managers = List.of(getManager(), getAnotherManager());
        var expectedResult = List.of(getManagerReadDto(), getAnotherManagerReadDto());
        doReturn(managers).when(managerRepository).findAll();
        doReturn(getManagerReadDto(), getAnotherManagerReadDto()).when(managerReadMapper).map(any(Manager.class));

        var actualResult = managerService.findAll();

        assertThat(actualResult).hasSize(ALL_MANAGERS);
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void findAllWithFilter() {
        var filter = new ManagerFilter("Lina", "Yermak", LocalDate.of(2007, 11, 23));
        var managers = List.of(getManager(), getAnotherManager());
        var expectedResult = List.of(getManagerReadDto(), getAnotherManagerReadDto());
        doReturn(managers).when(managerRepository).findAllByFilter(filter);
        doReturn(getManagerReadDto(), getAnotherManagerReadDto()).when(managerReadMapper).map(any(Manager.class));
        var actualResult = managerService.findAll(filter);

        assertThat(actualResult).hasSize(2);
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void findById() {
        var manager = getManager();
        var expectedResult = getManagerReadDto();
        doReturn(Optional.of(manager)).when(managerRepository).findById(MANAGER_ID_ONE);
        doReturn(expectedResult).when(managerReadMapper).map(manager);

        var actualResult = managerService.findById(MANAGER_ID_ONE);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

    @Test
    void create() {
        var managerCreateEditDto = getManagerCreateDto();
        var manager = getManager();
        var expectedResult = getManagerReadDto();
        doReturn(manager).when(managerCreateEditMapper).map(managerCreateEditDto);
        doReturn(manager).when(managerRepository).save(manager);
        doReturn(expectedResult).when(managerReadMapper).map(manager);

        var actualResult = managerService.create(managerCreateEditDto);

        assertThat(actualResult.getId()).isNotNull();
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    @Test
    void update() {
        var manager = getManager();
        var managerCreateEditDto = getUpdatedManagerDto();
        var updatedManager = getUpdatedManager();
        var expectedResult = getUpdatedManagerReadDto();
        doReturn(Optional.of(manager)).when(managerRepository).findById(MANAGER_ID_ONE);
        doReturn(updatedManager).when(managerCreateEditMapper).map(managerCreateEditDto, manager);
        doReturn(updatedManager).when(managerRepository).saveAndFlush(updatedManager);
        doReturn(expectedResult).when(managerReadMapper).map(updatedManager);

        var actualResult = managerService.update(MANAGER_ID_ONE, managerCreateEditDto);

        assertThat(Optional.of(expectedResult)).isEqualTo(actualResult);
    }

    @Test
    void delete() {
        var manager = getManager();
        doReturn(Optional.of(manager)).when(managerRepository).findById(MANAGER_ID_ONE);

        var expectedResult = managerService.delete(MANAGER_ID_ONE);

        assertTrue(expectedResult);
    }

    private Manager getManager() {
        return Manager.builder()
                .id(MANAGER_ID_ONE)
                .lastname("Lina")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1997, 12, 12))
                .hireDate(LocalDate.of(2007, 11, 23))
                .build();
    }

    private Manager getAnotherManager() {
        return Manager.builder()
                .id(MANAGER_ID_TWO)
                .lastname("Lina")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1986, 10, 1))
                .hireDate(LocalDate.of(2007, 11, 23))
                .build();
    }

    private ManagerReadDto getManagerReadDto() {
        return new ManagerReadDto(
                MANAGER_ID_ONE,
                "Lina",
                "Yermak",
                LocalDate.of(1997, 12, 12),
                LocalDate.of(2007, 11, 23)
        );
    }

    private ManagerReadDto getAnotherManagerReadDto() {
        return new ManagerReadDto(
                MANAGER_ID_TWO,
                "Lina",
                "Yermak",
                LocalDate.of(1986, 10, 1),
                LocalDate.of(2007, 11, 23)
        );
    }

    private ManagerCreatEditDto getManagerCreateDto() {
        return new ManagerCreatEditDto(
                "Pavel",
                "Zhuk",
                LocalDate.of(1996, 7, 17),
                LocalDate.of(2022, 1, 14)
        );
    }

    private ManagerCreatEditDto getUpdatedManagerDto() {
        return new ManagerCreatEditDto(
                "Pavel",
                "Zhuk",
                LocalDate.of(1996, 7, 17),
                LocalDate.of(2022, 1, 14)
        );
    }

    private Manager getUpdatedManager() {
        return Manager.builder()
                .id(MANAGER_ID_ONE)
                .lastname("NewName")
                .firstname("Yermak")
                .birthDate(LocalDate.of(1997, 12, 12))
                .hireDate(LocalDate.of(2007, 11, 23))
                .build();
    }

    private ManagerReadDto getUpdatedManagerReadDto() {
        return new ManagerReadDto(
                MANAGER_ID_ONE,
                "NewName",
                "Yermak",
                LocalDate.of(1997, 12, 12),
                LocalDate.of(2007, 11, 23)
        );
    }
}
