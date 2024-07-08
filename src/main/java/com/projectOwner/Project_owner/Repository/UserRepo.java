package com.projectOwner.Project_owner.Repository;

import com.projectOwner.Project_owner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = " SELECT * FROM USER WHERE email = :email AND PASSWORD = :password ", nativeQuery = true)
    User getByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    @Query(value = " SELECT email FROM USER WHERE deleted = FALSE ", nativeQuery = true)
    List<String> emails();

    @Query(value = " SELECT * FROM user WHERE deleted = FALSE ", nativeQuery = true)
    List<User> findUsers();
}