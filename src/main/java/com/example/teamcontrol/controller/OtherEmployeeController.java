package com.example.teamcontrol.controller;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeCreatEditDto;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeFilter;
import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeReadDto;
import com.example.teamcontrol.service.OtherEmployeeService;
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
@RequestMapping("/api/v1/otherEmployees")
@RequiredArgsConstructor
public class OtherEmployeeController {

    private final OtherEmployeeService otherOtherEmployeeService;

    @GetMapping
    public List<OtherEmployeeReadDto> findAll(OtherEmployeeFilter filter) {
        return otherOtherEmployeeService.findAll(filter);
    }

    @GetMapping("/{id}")
    public OtherEmployeeReadDto findById(@PathVariable("id") Long id) {
        return otherOtherEmployeeService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OtherEmployeeReadDto create(@Validated @RequestBody OtherEmployeeCreatEditDto otherOtherEmployee) {
        return otherOtherEmployeeService.create(otherOtherEmployee);
    }

    @PutMapping("/{id}/update")
    public OtherEmployeeReadDto update(@PathVariable("id") Long id,
                                       @Validated @RequestBody OtherEmployeeCreatEditDto otherOtherEmployee) {
        return otherOtherEmployeeService.update(id, otherOtherEmployee)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return otherOtherEmployeeService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
