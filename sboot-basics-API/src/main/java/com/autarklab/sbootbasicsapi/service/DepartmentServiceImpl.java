package com.autarklab.sbootbasicsapi.service;

import com.autarklab.sbootbasicsapi.ErrorHandler.DepartmentNotFoundException;
import com.autarklab.sbootbasicsapi.entity.Department;
import com.autarklab.sbootbasicsapi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository repository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return repository.findAll();
    }

    @Override
    public Department getDepartment(Long id) throws DepartmentNotFoundException {
        Optional<Department> department = repository.findById(id);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department with id: " + id + ". Not found");
        }

        return department.get();
    }

    @Override
    public Boolean isPresentDepartment(Long id) {
        return repository.findById(id).isPresent();
    }

    @Override
    public Department modifyDepartment(Long id, Department department) throws DepartmentNotFoundException {
        if (isPresentDepartment(id)){
            Department newDepartment = getDepartment(id);
            if(this.validationFields(
                    department.getDepartmentName(),
                    department.getDepartmentAddress(),
                    department.getDepartmentCode())){

                newDepartment.setDepartmentName(department.getDepartmentName());
                newDepartment.setDepartmentAddress(department.getDepartmentAddress());
                newDepartment.setDepartmentCode(department.getDepartmentCode());

                return repository.save(newDepartment);
            }
            return null;
        }
        return null;
    }

    @Override
    public Department getByName(String name) {
        return repository.findDepartmentByDepartmentNameIgnoreCase(name);
    }

    @Override
    public String deleteDepartment(Long id) {
        if (isPresentDepartment(id)){
            repository.deleteById(id);
            return "Department: " +id + " deleted!.";
        }
        return "Department: " +id + " Not found!. Cannot be deleted";
    }

    private boolean validationFields(String name, String address, String code){

        if(Objects.isNull(name) || "".equalsIgnoreCase(name)){
            return false;
        }

        if(Objects.isNull(address) || "".equalsIgnoreCase(address)){
            return false;
        }

        if(Objects.isNull(code) || "".equalsIgnoreCase(code)){
            return false;
        }

        return true;
    }

}
