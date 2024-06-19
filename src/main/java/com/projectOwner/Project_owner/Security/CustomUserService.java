package com.projectOwner.Project_owner.Security;

import com.projectOwner.Project_owner.Entity.User;
import com.projectOwner.Project_owner.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService {

    @Autowired
    private UserRepo userRepo;

    public User loadUserByUsername(String username){
    User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found.."));
    return new User(user.getUsername(), user.getEmail(), user.getPassword());
    }
}
