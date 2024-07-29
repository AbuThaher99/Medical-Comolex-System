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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserController extends SessionManagement {

    private final AuthenticationService service;

    @PostMapping("/")
    public ResponseEntity<AuthenticationResponse> adduser(@RequestBody @Valid User request, HttpServletRequest httpServletRequest) throws UserNotFoundException, IOException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
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
    public Page<User> getallusers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String search ,  @RequestParam(defaultValue = "") Role role,HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
          validateLoggedInAdmin(user);
        return service.GetAllUsers(page, size,search,role);
    }

    @GetMapping("byRole/{role}")
    public Page<User> getAllUsersByRole(@PathVariable Role role,@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
         validateLoggedInAdmin(user);
        return service.getAllUsersByRole(role, page, size);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PutMapping("/UploadImage/{id}")
    public String uploadImage(@RequestParam("file") MultipartFile file,@PathVariable Long id ,HttpServletRequest httpServletRequest) throws UserNotFoundException, IOException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return service.uploadImageToFileSystem(file, id);
    }

}
