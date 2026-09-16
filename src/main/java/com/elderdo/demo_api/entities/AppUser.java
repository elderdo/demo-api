package com.elderdo.demo_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "Users")
public class AppUser {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "PasswordHash")
    private byte[] passwordHash; // Maps to EF Core's byte[] array column

    @Column(name = "PasswordSalt")
    private byte[] passwordSalt; // Maps to EF Core's byte[] array column

    public AppUser() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public byte[] getPasswordHash() { return passwordHash; }
    public void setPasswordHash(byte[] passwordHash) { this.passwordHash = passwordHash; }
    public byte[] getPasswordSalt() { return passwordSalt; }
    public void setPasswordSalt(byte[] passwordSalt ) { this.passwordSalt = passwordSalt; }
}
