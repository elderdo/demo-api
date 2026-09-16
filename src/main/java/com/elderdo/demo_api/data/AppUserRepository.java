package com.elderdo.demo_api.data;

import com.elderdo.demo_api.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; // <-- Ensure this import is present

@Repository // <-- Reinstate this explicit indicator
public interface AppUserRepository extends JpaRepository<AppUser, String> {
}
