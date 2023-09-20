package com.autarklab.sbootbasicsapi.controller;

import com.autarklab.sbootbasicsapi.ErrorHandler.DepartmentNotFoundException;
import com.autarklab.sbootbasicsapi.entity.Department;
import com.autarklab.sbootbasicsapi.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentName("cacas")
                .departmentAddress("costeño")
                .departmentCode("petro")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department InpDepartment = Department.builder()
                .departmentName("cacas")
                .departmentAddress("costenio")
                .departmentCode("petro")
                .departmentId(1L)
                .build();

        Mockito.when(departmentService.saveDepartment(InpDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"cacas\",\n" +
                        "\t\"departmentAddress\":\"costenio\",\n" +
                        "\t\"departmentCode\":\"petro\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getDepartment() throws Exception {
        Mockito.when(departmentService.getDepartment(1L))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));
    }
}