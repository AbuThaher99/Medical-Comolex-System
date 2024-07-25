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

}
