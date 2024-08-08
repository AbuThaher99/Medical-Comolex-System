package org.example.ProjectTraninng;

import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;

public class SessionManagement {
    public void validateLoggedInAdmin(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }

    public void validateLoggedInDoctor(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN && user.getRole() != Role.DOCTOR){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }

    public void validateLoggedInSecretary(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN && user.getRole() != Role.SECRETARY){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }

    public void validateLoggedInWarehouseEmployee(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN && user.getRole() != Role.WAREHOUSE_EMPLOYEE){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }

    public void validateLoggedInPatient(User user) throws UserNotFoundException {
        if(user.getRole() != Role.PATIENT){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }

    public void validateLoggedInAll(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN && user.getRole() != Role.SECRETARY && user.getRole() != Role.PATIENT){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }


    public void validateLoggedInCheckInOut(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN && user.getRole() != Role.SECRETARY && user.getRole() != Role.DOCTOR && user.getRole() != Role.WAREHOUSE_EMPLOYEE){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }


}
