package org.example.ProjectTraninng.WebApi.Controllers.Admin.Users;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.ProjectTraninng.Common.DTOs.LoginDTO;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController extends SessionManagement {

    private final AuthenticationService service;

    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> adduser(@RequestBody @Valid User request) throws UserNotFoundException {
//        String token = extractToken(httpServletRequest);
//        User user = service.extractUserFromToken(token);
//        validateLoggedInAdmin(user);
//        , HttpServletRequest httpServletRequest

        return ResponseEntity.ok(service.adduser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthenticationResponse> updateuser(@RequestBody @Valid User request,@PathVariable Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return ResponseEntity.ok(service.UpdateUser(request,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthenticationResponse> deleteuser(@PathVariable Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
          validateLoggedInAdmin(user);
        return ResponseEntity.ok(service.DeleteUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getuser(@PathVariable Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return ResponseEntity.ok(service.GetUser(id));
    }

    @GetMapping("")
    public  GeneralResponse<User> getallusers(HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
          validateLoggedInAdmin(user);
          List<User> users = service.GetAllUsers();
        GeneralResponse<User> response = new GeneralResponse<>();
        response.setList(users);
        response.setCount(10);
        return response;
    }

    @GetMapping("/{role}")
    public ResponseEntity<List<User>> getAllUsersByRole(@PathVariable Role role, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
         validateLoggedInAdmin(user);
        return ResponseEntity.ok(service.getAllUsersByRole(role));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    public void validateLoggedInAdmin(User user) throws UserNotFoundException {
       if(user.getRole() != Role.ADMIN){
           throw new UserNotFoundException("You are not authorized to perform this operation");
       }
     }

}
