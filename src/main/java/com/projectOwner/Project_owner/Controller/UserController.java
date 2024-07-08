package com.projectOwner.Project_owner.Controller;

import com.projectOwner.Project_owner.DTO.LoginDto;
import com.projectOwner.Project_owner.DTO.UserDto;
import com.projectOwner.Project_owner.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "user")
@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserDto> getAll(){
        return service.getAllData();
    }

    @PostMapping
    public UserDto addData(@RequestBody UserDto userDto){
        return service.saveData(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping()
    public ResponseEntity<UserDto> putProject(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.updateData(userDto));
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        service.deleteData(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
       return ResponseEntity.ok(service.loginUser(loginDto.getEmail(), loginDto.getPassword()));
    }

}
