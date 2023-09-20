package com.autarklab.sbootbasicsapi.controller;

import com.autarklab.sbootbasicsapi.entity.Department;
import com.autarklab.sbootbasicsapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public Department saveDepartment(@RequestBody Department department){
        return service.saveDepartment(department);
    }

    @GetMapping
    public List<Department> getDepartments(){
        return service.getDepartments();
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable(name = "id") Long id) {
        return service.getDepartment(id);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable(name = "id") Long id){
        return service.deleteDepartment(id);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable(name = "id") Long id,
                                        @RequestBody Department department){
        return service.modifyDepartment(id,department);
    }

    @GetMapping("/name/{name}")
    public Department getDepartmentByName(@PathVariable(name = "name") String name){
        return service.getByName(name);
    }
}