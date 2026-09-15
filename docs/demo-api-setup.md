# Spring Boot Project Setup Guide

This document captures the exact configuration and steps used to initialize your `demo-api` project in VS Code using the Spring Initializr extension.

---

## 🛠️ Project Specifications
* **Build Tool:** Maven Project
* **Spring Boot Version:** 4.1.1 (Latest Stable)
* **Language:** Java
* **Group ID:** `com.example`
* **Artifact ID:** `demo-api`
* **Packaging Type:** `Jar`
* **Java Version Compatibility:** Java 17 or Java 21 (Spring Boot 4 base requirement)
* **Dependencies Included:** `Spring Web` (for building REST APIs and web apps)

---

## 🚀 Execution Steps in VS Code

### Step 1: Launch Spring Initializr
1. Open a fresh, blank window in **VS Code**.
2. Press **`Ctrl + Shift + P`** to open the Command Palette.
3. Type **`Spring Initializr`** and select **`Spring Initializr: Create a Maven Project...`**.

### Step 2: Navigate the Prompts
Provide the configuration values sequentially when prompted by the wizard:
1. **Spring Boot Version:** Select `4.1.1`.
2. **Language:** Select `Java`.
3. **Group ID:** Type `com.example` and press Enter.
4. **Artifact ID:** Type `demo-api` and press Enter.
5. **Packaging Type:** Select `Jar`.
6. **Java Version:** Select your installed JDK version (`17` or `21`).
7. **Dependencies:** 
   * Type `Spring Web` in the search box.
   * Click the checkmark box next to it to include it.
   * Press **Enter** to finalize selection.

### Step 3: Save and Initialize
1. A file explorer window will open. Navigate to your working space directory (e.g., `C:\Users\Douglas\Documents\java`).
2. Click **Generate into this folder**.
3. Look for the system notification toast in the bottom right corner of VS Code saying *"Successfully generated..."*.
4. Click the **Open** button inside that toast notification to load your new project automatically.

---

## 📝 Post-Generation Checklist
* **Project Root:** Always make sure VS Code is opened directly to the `demo-api` folder level so the integrated build tools can see the root `pom.xml`.
* **Activation Delay:** Allow 10–15 seconds on initial load for the Java Language Support extensions to index dependencies and download the underlying Spring libraries.
