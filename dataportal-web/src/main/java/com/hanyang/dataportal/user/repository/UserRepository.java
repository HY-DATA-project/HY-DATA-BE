package com.hanyang.dataportal.user.repository;

import com.hanyang.dataportal.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isActive = true")
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.name = :name and u.isActive = true")
    Optional<User> findByName(String name);
}
