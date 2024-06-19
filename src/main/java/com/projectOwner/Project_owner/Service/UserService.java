package com.projectOwner.Project_owner.Service;

import com.projectOwner.Project_owner.DTO.UserDto;
import com.projectOwner.Project_owner.Entity.User;
import com.projectOwner.Project_owner.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired

    public List<UserDto> getAllData(){
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user: users){
            UserDto userDto = new UserDto();
            mapToDto(userDto,user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    public UserDto saveData(UserDto userDto){
        List<String> emails = userRepo.emails();

        if (emails.contains(userDto.getEmail())){
            throw new RuntimeException("Email Exists..");
        } else {
            User user = new User();
            mapToEntity(userDto,user);
            userRepo.save(user);
            return userDto;
        }
        //null can be returned
    }

    public UserDto getById(Long id){
        UserDto userDto = new UserDto();
        User user = userRepo.findById(id).get();
        mapToDto(userDto,user);
        return userDto;
    }

    public void deleteData(Long id){
        User user = userRepo.findById(id).get();
        if (user != null){
            user.setDeleted(true);
            userRepo.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }


    public User mapToEntity(UserDto userDto, User user){
        if (user != null){
            user.setUserId(userDto.getUserId());
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public UserDto mapToDto(UserDto userDto, User user){
        if (userDto != null){
            userDto.setUserId(user.getUserId());
        }
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public UserDto loginUser(String email, String password) {
        User user = userRepo.getByEmailAndPassword(email, password);
        if (user != null){
            UserDto userDto = new UserDto();
            return mapToDto(userDto,user);
        } else {
            throw new RuntimeException("User not found..");
        }
    }
}
