package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Department;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Repsitories.DepartmentRepsitory;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Core.Repsitories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    @Autowired
    private DepartmentRepsitory departmentRepository;
    @Autowired
    private  UserRepository userRepository;

    @Transactional
    public GeneralResponse addDepartment(Department request) throws UserNotFoundException {
        Optional<User> headSecretary = userRepository.findById(request.getHeadId().getId());
        if (headSecretary.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        Optional<User> secretary = userRepository.findById(request.getSecretaryId().getId());
        if (secretary.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        Department department = Department.builder()
                .name(request.getName())
                .headId(headSecretary.get()).
                secretaryId(secretary.get())
                .build();
        departmentRepository.save(department);
      return   GeneralResponse.builder().message("Department added successfully").build();
    }

    @Transactional
    public void updateDepartment(Department request, Long departmentId) throws UserNotFoundException {

        var department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new UserNotFoundException("Department not found"));
        department.setName(request.getName());

        User headSecretary = userRepository.findById(request.getHeadId().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        department.setHeadId(headSecretary);

        User secretary = userRepository.findById(request.getSecretaryId().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        department.setSecretaryId(secretary);
        departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long departmentId) throws UserNotFoundException {
        var department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new UserNotFoundException("Department not found"));
        department.setDeleted(true);
        departmentRepository.save(department);
    }
    @Transactional
    public Department findDepartmentById(Long departmentId) throws UserNotFoundException {
  var department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new UserNotFoundException("Department not found"));
        return department;

    }

    @Transactional
    public Page<Department> getAllDepartment(int page, int size , String search) {
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        return departmentRepository.findAll(pageable,search);
    }
}
