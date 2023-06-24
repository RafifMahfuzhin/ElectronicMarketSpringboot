package com.repository;


import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    void delete(User user);

    @Query("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE r.name = 'ROLE_USER'")
    int countUsersWithUserRole();

}