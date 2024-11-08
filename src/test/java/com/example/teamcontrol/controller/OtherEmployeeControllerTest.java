package com.example.teamcontrol.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.teamcontrol.dto.OtherEmployeeDto.OtherEmployeeCreatEditDto;
import com.example.teamcontrol.integration.IntegrationTestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class OtherEmployeeControllerTest extends IntegrationTestBase {

    private static final String URL_REST_PREFIX = "/api/v1/otherEmployees";
    private static final Long OTHER_EMPLOYEE_ID_ONE = 1L;
    public static final Integer POSITION_ID_ONE = 1;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get(URL_REST_PREFIX))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].position").exists());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get(URL_REST_PREFIX + "/" + OTHER_EMPLOYEE_ID_ONE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lastname").value("Kimstach"));
    }

    @Test
    void createOtherEmployeeSuccessfully() throws Exception {
        OtherEmployeeCreatEditDto newOtherEmployeeDto = new OtherEmployeeCreatEditDto(
                "John",
                "Doe",
                LocalDate.of(1995, 6, 24),
                LocalDate.of(2024, 1, 15),
                POSITION_ID_ONE
        );

        mockMvc.perform(post(URL_REST_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newOtherEmployeeDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.birthDate").value("1995-06-24"))
                .andExpect(jsonPath("$.hireDate").value("2024-01-15"))
                .andExpect(jsonPath("$.position.id").value("1"));
    }

    @Test
    void updateOtherEmployeeSuccessfully() throws Exception {
        OtherEmployeeCreatEditDto updatedOtherEmployeeDto = new OtherEmployeeCreatEditDto(
                "Jane",
                "Smith",
                LocalDate.of(1997, 7, 19),
                LocalDate.of(2023, 3, 10),
                POSITION_ID_ONE
        );

        mockMvc.perform(put(URL_REST_PREFIX + "/" + OTHER_EMPLOYEE_ID_ONE + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedOtherEmployeeDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname").value("Jane"))
                .andExpect(jsonPath("$.lastname").value("Smith"))
                .andExpect(jsonPath("$.birthDate").value("1997-07-19"))
                .andExpect(jsonPath("$.hireDate").value("2023-03-10"))
                .andExpect(jsonPath("$.position.id").value("1"));
    }

    @Test
    void deleteSuccessfully() throws Exception {
        mockMvc.perform(delete(URL_REST_PREFIX + "/" + OTHER_EMPLOYEE_ID_ONE + "/delete"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteNonExistentOtherEmployee() throws Exception {
        long nonExistentId = 999999999999999L;
        mockMvc.perform(delete(URL_REST_PREFIX + "/" + nonExistentId + "/delete"))
                .andExpect(status().isNotFound());
    }
}
