package com.elderdo.demo_api.data;

import com.elderdo.demo_api.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    // Inherits .findAll() and .findById() hooks instantly using Spring Dependency Injection
}
