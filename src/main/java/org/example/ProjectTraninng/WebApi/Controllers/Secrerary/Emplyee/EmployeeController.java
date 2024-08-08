package org.example.ProjectTraninng.WebApi.Controllers.Secrerary.Emplyee;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/secretary/employee")
public class EmployeeController extends SessionManagement {
   @Autowired
    private final AuthenticationService authenticationService;

   @PostMapping("/CheckIn")
    public GeneralResponse CheckIn(@RequestParam(defaultValue = "") Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
       String token = authenticationService.extractToken(httpServletRequest);
       User user = authenticationService.extractUserFromToken(token);
       validateLoggedInCheckInOut(user);
        return authenticationService.CheckIn(id, user.getId());
    }

    @PostMapping("/CheckOut")
    public GeneralResponse CheckOut(@RequestParam(defaultValue = "")Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = authenticationService.extractToken(httpServletRequest);
        User user = authenticationService.extractUserFromToken(token);
        validateLoggedInCheckInOut(user);
        return authenticationService.checkOut(id, user.getId());
    }
}
