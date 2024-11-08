package com.example.teamcontrol.service;

import com.example.teamcontrol.database.repository.EmployeeRepository;
import com.example.teamcontrol.dto.employeeDto.EmployeeCreatEditDto;
import com.example.teamcontrol.dto.employeeDto.EmployeeFilter;
import com.example.teamcontrol.dto.employeeDto.EmployeeReadDto;
import com.example.teamcontrol.mapper.employeeMapper.EmployeeCreateEditMapper;
import com.example.teamcontrol.mapper.employeeMapper.EmployeeReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeReadMapper employeeReadMapper;
    private final EmployeeCreateEditMapper employeeCreateEditMapper;

    public List<EmployeeReadDto> findAll(EmployeeFilter filter) {
        return employeeRepository.findAllByFilter(filter).stream()
                .map(employeeReadMapper::map)
                .toList();
    }

    public Optional<EmployeeReadDto> findById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeReadMapper::map);
    }

    public List<EmployeeReadDto> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeReadMapper::map)
                .toList();
    }

    @Transactional
    public EmployeeReadDto create(EmployeeCreatEditDto employeeDto) {
        return Optional.of(employeeDto)
                .map(employeeCreateEditMapper::map)
                .map(employeeRepository::save)
                .map(employeeReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<EmployeeReadDto> update(Long id, EmployeeCreatEditDto employeeDto) {
        return employeeRepository.findById(id)
                .map(entity -> employeeCreateEditMapper.map(employeeDto, entity))
                .map(employeeRepository::saveAndFlush)
                .map(employeeReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return employeeRepository.findById(id)
                .map(entity -> {
                    employeeRepository.delete(entity);
                    employeeRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
