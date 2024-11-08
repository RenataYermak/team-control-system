package com.example.teamcontrol.service;

import com.example.teamcontrol.database.repository.ManagerRepository;
import com.example.teamcontrol.dto.managerDto.ManagerCreatEditDto;
import com.example.teamcontrol.dto.managerDto.ManagerFilter;
import com.example.teamcontrol.dto.managerDto.ManagerReadDto;
import com.example.teamcontrol.mapper.managerMapper.ManagerCreateEditMapper;
import com.example.teamcontrol.mapper.managerMapper.ManagerReadMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerReadMapper managerReadMapper;
    private final ManagerCreateEditMapper managerCreateEditMapper;

    public List<ManagerReadDto> findAll(ManagerFilter filter) {
        return managerRepository.findAllByFilter(filter).stream()
                .map(managerReadMapper::map)
                .toList();
    }

    public Optional<ManagerReadDto> findById(Long id) {
        return managerRepository.findById(id)
                .map(managerReadMapper::map);
    }

    public List<ManagerReadDto> findAll() {
        return managerRepository.findAll().stream()
                .map(managerReadMapper::map)
                .toList();
    }

    @Transactional
    public ManagerReadDto create(ManagerCreatEditDto managerDto) {
        return Optional.of(managerDto)
                .map(managerCreateEditMapper::map)
                .map(managerRepository::save)
                .map(managerReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ManagerReadDto> update(Long id, ManagerCreatEditDto managerDto) {
        return managerRepository.findById(id)
                .map(entity -> managerCreateEditMapper.map(managerDto, entity))
                .map(managerRepository::saveAndFlush)
                .map(managerReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return managerRepository.findById(id)
                .map(entity -> {
                    managerRepository.delete(entity);
                    managerRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
