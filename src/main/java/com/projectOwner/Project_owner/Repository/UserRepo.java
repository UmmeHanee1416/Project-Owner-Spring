package com.projectOwner.Project_owner.Repository;

import com.projectOwner.Project_owner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = " SELECT * FROM USER WHERE email = :email AND PASSWORD = :password ", nativeQuery = true)
    User getByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    @Query(value = " SELECT email FROM USER ", nativeQuery = true)
    List<String> emails();

    Optional<User> findByEmail(String username);
}
