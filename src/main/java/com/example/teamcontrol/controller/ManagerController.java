package com.example.teamcontrol.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

import com.example.teamcontrol.dto.managerDto.ManagerCreatEditDto;
import com.example.teamcontrol.dto.managerDto.ManagerFilter;
import com.example.teamcontrol.dto.managerDto.ManagerReadDto;
import com.example.teamcontrol.service.ManagerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping
    public List<ManagerReadDto> findAll(ManagerFilter filter) {
        return managerService.findAll(filter);
    }

    @GetMapping("/{id}")
    public ManagerReadDto findById(@PathVariable("id") Long id) {
        return managerService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerReadDto create(@Validated @RequestBody ManagerCreatEditDto manager) {
        return managerService.create(manager);
    }

    @PutMapping("/{id}/update")
    public ManagerReadDto update(@PathVariable("id") Long id,
                                 @Validated @RequestBody ManagerCreatEditDto manager) {
        return managerService.update(id, manager)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return managerService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
