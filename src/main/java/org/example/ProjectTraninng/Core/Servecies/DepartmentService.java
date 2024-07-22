package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.DepartmentDTO;
import org.example.ProjectTraninng.Common.DTO.DepartmentRequest;
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
    public DepartmentResponse addDepartment(DepartmentRequest request) throws UserNotFoundException {

        Optional<User> headSecretary = userRepository.findById(request.getHeadDepartmentId());
        if (headSecretary.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        Optional<User> secretary = userRepository.findById(request.getSecretaryId());
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
    public void updateDepartment(DepartmentRequest request, Long departmentId) throws UserNotFoundException {
        var department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new UserNotFoundException("Department not found"));
        department.setName(request.getName());

        User headSecretary = userRepository.findById(request.getHeadDepartmentId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        department.setHeadDepartment(headSecretary);

        User secretary = userRepository.findById(request.getSecretaryId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        department.setSecretary(secretary);
        departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long departmentId) throws UserNotFoundException {
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
    public List<DepartmentRequest> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentRequest> departmentRequests = new ArrayList<>();
        for (Department department : departments) {
            departmentRequests.add(DepartmentRequest.builder()
                    .name(department.getName())
                    .headDepartmentId(department.getHeadDepartment().getId())
                    .secretaryId(department.getSecretary().getId())
                    .build());
        }
        return departmentRequests;
    }
}
