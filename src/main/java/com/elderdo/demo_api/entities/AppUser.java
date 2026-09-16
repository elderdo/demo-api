package com.elderdo.demo_api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "Users") // Explicitly targets the table name inside your dating.db SQLite file
public class AppUser {

    @Id
    @Column(name = "Id") // Matches exact casing of SQLite columns from EF Core
    private String id;

    @Column(name = "UserName")
    private String userName;

    // Required by Hibernate
    public AppUser() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
