package com.example.teamcontrol.service;

import com.example.teamcontrol.database.repository.PositionRepository;
import com.example.teamcontrol.dto.OtherEmployeeDto.PositionReadDto;
import com.example.teamcontrol.mapper.otherEmployeeMapper.PositionReadMapper;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final PositionReadMapper positionReadMapper;

    public List<PositionReadDto> findAll() {
        return positionRepository.findAll().stream()
                .map(positionReadMapper::map)
                .toList();
    }
}
