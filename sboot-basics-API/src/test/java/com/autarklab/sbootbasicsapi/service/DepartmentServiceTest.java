package com.autarklab.sbootbasicsapi.service;

import com.autarklab.sbootbasicsapi.entity.Department;
import com.autarklab.sbootbasicsapi.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Bogota")
                        .departmentCode("TI-B01")
                        .departmentId(1L)
                        .build();

        Mockito.when(departmentRepository.findDepartmentByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on valid Department Name")
    public void when_valid_departmentName_then_department_should_be_found() {
        String departmentName = "IT";
        Department found = departmentService.getByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }
}
