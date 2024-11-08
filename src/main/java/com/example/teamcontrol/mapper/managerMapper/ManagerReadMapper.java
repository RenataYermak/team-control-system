package com.example.teamcontrol.mapper.managerMapper;

import com.example.teamcontrol.database.entity.Manager;
import com.example.teamcontrol.dto.managerDto.ManagerReadDto;
import com.example.teamcontrol.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerReadMapper implements Mapper<Manager, ManagerReadDto> {

    @Override
    public ManagerReadDto map(Manager object) {
        return new ManagerReadDto(
                object.getId(),
                object.getFirstname(),
                object.getLastname(),
                object.getBirthDate(),
                object.getHireDate()
        );
    }
}
