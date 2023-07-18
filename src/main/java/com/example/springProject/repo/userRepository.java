package com.example.springProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springProject.Entity.User;


public interface userRepository extends JpaRepository<User, Long>{
    
}
