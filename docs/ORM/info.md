# Java ORM (Spring Data JPA & Hibernate) Cheat Sheet

This guide breaks down the standard tutorials, documentation resources, and the necessary "annotation soup" required to handle database operations in a modern Java backend environment.

---

## 1. Top Recommended Tutorials & Resources

When brushing up on Java's data layer, use these trusted, no-nonsense developer resources:

- **Baeldung (Universal Code-First Manual)**: The absolute best resource for punchy, concise text tutorials.
  - _Search for_: `"Baeldung Introduction to Spring Data JPA"` and `"Baeldung Hibernate Annotations Guide"`.
- **Spring Academy (Official Spring Platform)**: Free guides built directly by VMware (the creators of Spring).
  - _Search for_: `"Spring Academy Accessing Data with JPA"`.
- **Vlad Mihalcea's Blog (Deep-Dive Performance)**: Written by a former Hibernate core engineer. Excellent for troubleshooting weird database mapping bugs.

---

## 2. Deciphering the "Annotation Soup" (The Cheat Sheet)

Because Java lacks native C#-style keywords for properties and data conventions, it relies heavily on metadata tags. Here is how a standard entity class maps to a SQL database table:

```java
package com.elderdo.demo_api;

import jakarta.persistence.*;

@Entity                 // 1. Tells Hibernate: "Turn this class into a DB table"
@Table(name = "items")  // 2. Explicitly sets the SQL table name to "items"
public class Product {

    @Id                 // 3. Flags this field as the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. Sets auto-increment (like SQL Server IDENTITY)
    private Long id;

    @Column(name = "item_name", nullable = false, length = 100) // 5. Customizes SQL column constraints
    private String name;

    private Double price; // 6. No annotation needed; Hibernate automatically maps this to a standard column

    // Standard getters/setters (or managed via Project Lombok @Data)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
```

---

## 3. Required Maven Dependencies (`pom.xml`)

To force your project to pull down these ORM capabilities and bind them to a lightweight local SQLite database, you need to add these two core dependencies into your project's `pom.xml`:

```xml
<dependencies>
    <!-- Spring Data JPA: Pulls in Hibernate, Entity Manager, and Core ORM libraries -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- SQLite Driver: Enables local, serverless database file generation -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.46.1.3</version> <!-- Or the latest stable version from your repository -->
    </dependency>
</dependencies>
```
