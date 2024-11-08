package com.example.teamcontrol.service;

import com.example.teamcontrol.database.repository.OtherEmployeeRepository;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeCreatEditDto;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeFilter;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeReadDto;
import com.example.teamcontrol.mapper.otherEmployeeMapper.OtherEmployeeCreateEditMapper;
import com.example.teamcontrol.mapper.otherEmployeeMapper.OtherEmployeeReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OtherEmployeeService {

    private final OtherEmployeeRepository otherEmployeeRepository;
    private final OtherEmployeeReadMapper otherEmployeeReadMapper;
    private final OtherEmployeeCreateEditMapper otherEmployeeCreateEditMapper;

    public List<OtherEmployeeReadDto> findAll(OtherEmployeeFilter filter) {
        return otherEmployeeRepository.findAllByFilter(filter).stream()
                .map(otherEmployeeReadMapper::map)
                .toList();
    }

    public Optional<OtherEmployeeReadDto> findById(Long id) {
        return otherEmployeeRepository.findById(id)
                .map(otherEmployeeReadMapper::map);
    }

    public List<OtherEmployeeReadDto> findAll() {
        return otherEmployeeRepository.findAll().stream()
                .map(otherEmployeeReadMapper::map)
                .toList();
    }

    @Transactional
    public OtherEmployeeReadDto create(OtherEmployeeCreatEditDto otherEmployeeDto) {
        return Optional.of(otherEmployeeDto)
                .map(otherEmployeeCreateEditMapper::map)
                .map(otherEmployeeRepository::save)
                .map(otherEmployeeReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<OtherEmployeeReadDto> update(Long id, OtherEmployeeCreatEditDto otherEmployeeDto) {
        return otherEmployeeRepository.findById(id)
                .map(entity -> otherEmployeeCreateEditMapper.map(otherEmployeeDto, entity))
                .map(otherEmployeeRepository::saveAndFlush)
                .map(otherEmployeeReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return otherEmployeeRepository.findById(id)
                .map(entity -> {
                    otherEmployeeRepository.delete(entity);
                    otherEmployeeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
