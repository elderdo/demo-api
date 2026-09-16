package com.elderdo.demo_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "Users") // Matches your EF Core table name
public class AppUser {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "DisplayName") // Crucial: Replaces userName with your real field!
    private String displayName;

    @Column(name = "Email") // Crucial: Added your email field!
    private String email;

    @Column(name = "PasswordHash")
    private byte[] passwordHash;

    @Column(name = "PasswordSalt")
    private byte[] passwordSalt;

    public AppUser() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public byte[] getPasswordHash() { return passwordHash; }
    public void setPasswordHash(byte[] passwordHash) { this.passwordHash = passwordHash; }

    public byte[] getPasswordSalt() { return passwordSalt; }
    public void setPasswordSalt(byte[] passwordSalt) { this.passwordSalt = passwordSalt; }
}
