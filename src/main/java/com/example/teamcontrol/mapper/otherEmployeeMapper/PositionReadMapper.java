package com.example.teamcontrol.mapper.otherEmployeeMapper;

import com.example.teamcontrol.database.entity.Position;
import com.example.teamcontrol.dto.OtherEmployeeDto.PositionReadDto;
import com.example.teamcontrol.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PositionReadMapper implements Mapper<Position, PositionReadDto> {

    @Override
    public PositionReadDto map(Position object) {
        return new PositionReadDto(
                object.getId(),
                object.getName()
        );
    }
}
