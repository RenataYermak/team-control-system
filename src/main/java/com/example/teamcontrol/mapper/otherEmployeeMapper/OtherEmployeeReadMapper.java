package com.example.teamcontrol.mapper.otherEmployeeMapper;

import com.example.teamcontrol.database.entity.OtherEmployee;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeReadDto;
import com.example.teamcontrol.mapper.Mapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtherEmployeeReadMapper implements Mapper<OtherEmployee, OtherEmployeeReadDto> {

    private final PositionReadMapper positionReadMapper;

    @Override
    public OtherEmployeeReadDto map(OtherEmployee object) {
        var position = Optional.ofNullable(object.getPosition())
                .map(positionReadMapper::map)
                .orElse(null);

        return new OtherEmployeeReadDto(
                object.getId(),
                object.getFirstname(),
                object.getLastname(),
                object.getBirthDate(),
                object.getHireDate(),
                position
        );
    }
}
