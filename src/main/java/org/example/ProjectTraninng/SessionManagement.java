package org.example.ProjectTraninng;

import org.example.ProjectTraninng.Common.Entities.Role;
import org.example.ProjectTraninng.Common.Entities.User;

public class SessionManagement {
    public void validateLoggedInAdmin(User user){
        if(user.getRole() != Role.ADMIN){
            throw new RuntimeException("You are not authorized to perform this operation");
        }
    }

}
