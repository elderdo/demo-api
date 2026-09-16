package com.elderdo.demo_api.controllers;

import com.elderdo.demo_api.data.AppUserRepository;
import com.elderdo.demo_api.entities.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members") // Extends prefix to -> /api/members
public class MembersController extends BaseApiController {

    private final AppUserRepository userRepository;

    // Constructor Injection injects your swappable database access automatically
    public MembersController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Matches your C# ToListAsync() endpoint logic
    @GetMapping
    public ResponseEntity<List<AppUser>> getMembers() {
        List<AppUser> members = userRepository.findAll();
        return ResponseEntity.ok(members);
    }

    // Matches your C# FindAsync(id) endpoint logic
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getMember(@PathVariable String id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
