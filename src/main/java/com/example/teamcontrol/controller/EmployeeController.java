package com.example.teamcontrol.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

import com.example.teamcontrol.dto.employeeDto.EmployeeCreatEditDto;
import com.example.teamcontrol.dto.employeeDto.EmployeeFilter;
import com.example.teamcontrol.dto.employeeDto.EmployeeReadDto;
import com.example.teamcontrol.service.EmployeeService;
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
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeReadDto> findAll(EmployeeFilter filter) {
        return employeeService.findAll(filter);
    }

    @GetMapping("/{id}")
    public EmployeeReadDto findById(@PathVariable("id") Long id) {
        return employeeService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeReadDto create(@Validated @RequestBody EmployeeCreatEditDto employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}/update")
    public EmployeeReadDto update(@PathVariable("id") Long id,
                                  @Validated @RequestBody EmployeeCreatEditDto employee) {
        return employeeService.update(id, employee)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return employeeService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
