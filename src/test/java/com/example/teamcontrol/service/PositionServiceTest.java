package com.example.teamcontrol.service;

import com.example.teamcontrol.database.entity.Position;
import com.example.teamcontrol.database.repository.PositionRepository;
import com.example.teamcontrol.dto.OtherEmployeeDto.PositionReadDto;
import com.example.teamcontrol.mapper.otherEmployeeMapper.PositionReadMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.teamcontrol.util.ConstantUtil.ALL_POSITIONS;
import static com.example.teamcontrol.util.ConstantUtil.POSITION_ID_ONE;
import static com.example.teamcontrol.util.ConstantUtil.POSITION_ID_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;
    @Mock
    private PositionReadMapper positionReadMapper;

    @InjectMocks
    private PositionService positionService;

    @Test
    void findAll() {
        var positions = List.of(getPosition(), getAnotherPosition());
        var expectedResult = List.of(getPositionReadDto(), getAnotherPositionReadDto());
        doReturn(positions).when(positionRepository).findAll();
        doReturn(getPositionReadDto(), getAnotherPositionReadDto()).when(positionReadMapper).map(any(Position.class));

        var actualResult = positionService.findAll();

        assertThat(actualResult).hasSize(ALL_POSITIONS);
        assertThat(expectedResult).isEqualTo(actualResult);
    }

    private Position getPosition() {
        return Position.builder()
                .name("Administrator")
                .build();
    }

    private Position getAnotherPosition() {
        return Position.builder()
                .name("SEO")
                .build();
    }

    private PositionReadDto getPositionReadDto() {
        return new PositionReadDto(
                POSITION_ID_ONE,
                "Administrator"
        );
    }

    private PositionReadDto getAnotherPositionReadDto() {
        return new PositionReadDto(
                POSITION_ID_TWO,
                "SEO"

        );
    }
}
