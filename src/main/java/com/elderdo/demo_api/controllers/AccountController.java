package com.elderdo.demo_api.controllers;

import com.elderdo.demo_api.data.AppUserRepository;
import com.elderdo.demo_api.dtos.LoginDto;
import com.elderdo.demo_api.dtos.RegisterDto;
import com.elderdo.demo_api.entities.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/account")
public class AccountController extends BaseApiController {

    private final AppUserRepository userRepository;

    // Constructor Injection handles passing database access seamlessly
    public AccountController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        String normalizedUsername = registerDto.getUsername().toLowerCase();

        // Check if user already exists in SQLite table
        // We can check manually or add a custom query lookup later
        boolean userExists = userRepository.findAll().stream()
                .anyMatch(u -> u.getUserName().toLowerCase().equals(normalizedUsername));

        if (userExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken");
        }

        // Generate a cryptographically secure random 64-byte salt buffer (Matches C# auto-salt)
        byte[] salt = new byte[64];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        // Compute the HMACSHA512 password hash byte buffer
        byte[] hash = computeHmacSha512(registerDto.getPassword(), salt);

        AppUser user = new AppUser();
        user.setId(UUID.randomUUID().toString()); // Generate a clean string-based unique ID
        user.setUserName(registerDto.getUsername());
        user.setPasswordHash(hash);
        user.setPasswordSalt(salt);

        userRepository.save(user); // Commits the user record to your SQLite file
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String normalizedUsername = loginDto.getUsername().toLowerCase();

        // Locate user in your local repository stream
        Optional<AppUser> userOpt = userRepository.findAll().stream()
                .filter(u -> u.getUserName().toLowerCase().equals(normalizedUsername))
                .findFirst();

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
        }

        AppUser user = userOpt.get();

        // Recompute the incoming plain text password using the stored key salt from database
        byte[] computedHash = computeHmacSha512(loginDto.getPassword(), user.getPasswordSalt());

        // Perform an industry-safe time-constant array comparison to check hashes byte-by-byte
        if (!MessageDigest.isEqual(computedHash, user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        return ResponseEntity.ok(user); // Login contract matches!
    }

    // Helper Cryptography Routine: Perfectly replicates C#'s HMACSHA512 engine signature
    private byte[] computeHmacSha512(String plainTextPassword, byte[] salt) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(salt, "HmacSHA512");
            Mac hmacSha512 = Mac.getInstance("HmacSHA512");
            hmacSha512.init(secretKey);
            return hmacSha512.doFinal(plainTextPassword.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Error computing password cryptographic signature", e);
        }
    }
}
