package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.DepartmentDTO;
import org.example.ProjectTraninng.Common.Entities.Department;
import org.example.ProjectTraninng.Common.Response.DepartmentResponse;
import org.example.ProjectTraninng.Core.Repsitory.DepartmentRepsitory;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Core.Repsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    @Autowired
    private DepartmentRepsitory departmentRepository;
    @Autowired
    private  UserRepository userRepository;

    @Transactional
    public DepartmentResponse addDepartment(Department request) throws UserNotFoundException {
        Optional<User> headSecretary = userRepository.findById(request.getHeadDepartment().getId());
        if (headSecretary.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        Optional<User> secretary = userRepository.findById(request.getSecretary().getId());
        if (secretary.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        Department department = Department.builder()
                .name(request.getName())
                .headDepartment(headSecretary.get()).
                secretary(secretary.get())
                .build();
        departmentRepository.save(department);
      return   DepartmentResponse.builder().message("Department added successfully").build();
    }

    @Transactional
    public void updateDepartment(Department request, Long departmentId) throws UserNotFoundException {

        var department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new UserNotFoundException("Department not found"));
        department.setName(request.getName());

        User headSecretary = userRepository.findById(request.getHeadDepartment().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        department.setHeadDepartment(headSecretary);

        User secretary = userRepository.findById(request.getSecretary().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        department.setSecretary(secretary);
        departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long departmentId) throws UserNotFoundException {

        if(departmentId.equals("")){
            throw new UserNotFoundException("Please fill all the fields");
        }
        if(departmentId == null){
            throw new UserNotFoundException("Please fill all the fields");

        }
        var department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new UserNotFoundException("Department not found"));
        departmentRepository.deleteById(departmentId);
    }

    public DepartmentDTO findDepartmentById(Long departmentId) throws UserNotFoundException {
        return departmentRepository.findById(departmentId)
                .map(department -> DepartmentDTO.builder()
                        .name(department.getName())
                        .build())
                .orElseThrow(() -> new UserNotFoundException("Department not found"));

    }

    @Transactional
    public List<DepartmentDTO> getAllDepartment() {
        List<DepartmentDTO> departments = new ArrayList<>();
        List<Department> allDepartments = departmentRepository.findAll();
        for (Department department : allDepartments) {
            DepartmentDTO department1 = DepartmentDTO.builder()
                    .id(department.getId())
                    .name(department.getName())
                    .headDepartment(department.getHeadDepartment())
                    .secretary(department.getSecretary())
                    .build();
            departments.add(department1);
        }
        return departments;
    }
}
