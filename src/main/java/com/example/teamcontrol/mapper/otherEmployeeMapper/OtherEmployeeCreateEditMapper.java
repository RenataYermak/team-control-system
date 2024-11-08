package com.example.teamcontrol.mapper.otherEmployeeMapper;

import com.example.teamcontrol.database.entity.OtherEmployee;
import com.example.teamcontrol.database.entity.Position;
import com.example.teamcontrol.database.repository.PositionRepository;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeCreatEditDto;
import com.example.teamcontrol.mapper.Mapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OtherEmployeeCreateEditMapper implements Mapper<OtherEmployeeCreatEditDto, OtherEmployee> {

    private final PositionRepository positionRepository;

    @Override
    public OtherEmployee map(OtherEmployeeCreatEditDto object) {
        OtherEmployee otherEmployee = new OtherEmployee();
        copy(object, otherEmployee);
        return otherEmployee;
    }

    @Override
    public OtherEmployee map(OtherEmployeeCreatEditDto fromObject, OtherEmployee toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(OtherEmployeeCreatEditDto object, OtherEmployee OtherEmployee) {
        OtherEmployee.setFirstname(object.getFirstname());
        OtherEmployee.setLastname(object.getLastname());
        OtherEmployee.setBirthDate(object.getBirthDate());
        OtherEmployee.setHireDate(object.getHireDate());
        OtherEmployee.setPosition(getPosition(object.getPositionId()).get());
    }

    public Optional<Position> getPosition(Integer positionId) {
        return Optional.ofNullable(positionId)
                .flatMap(positionRepository::findById);
    }
}
