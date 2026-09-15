# Spring Boot Execution & Testing Guide

This guide documents the exact commands and verification steps required to run and test your `demo-api` project locally.

---

## 1. Running the Server

Because the local project wrapper (`mvnw`) may look for external downloads, always use your globally configured Maven installation directly through the terminal.

### The Command

Open your terminal in the project root directory (`C:\Users\Douglas\Documents\java\demo-api`) and run:

```cmd
mvn spring-boot:run
```

---

## 2. Verifying the Logs

As the application starts up, watch for the following key milestones in your scrolling terminal output:

1. **Spring Banner Initialization**:
   ```text
     .   ____          _            __ _ _
    /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
   ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
    \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
     '  |____| .__|_| |_|_| |_\__, | / / / /
    =========|_|==============|___/=/_/_/_/
    :: Spring Boot ::                (v4.1.1)
   ```
2. **Tomcat Server Port Allocation**:
   Look for confirmation that the server container is listening on port `8080`:
   ```text
   Tomcat initialized with port 8080 (http)
   ```
3. **Execution Success Flag**:
   The final line indicates the application is live and running in the background:
   ```text
   Started DemoApiApplication in 1.155 seconds
   ```

---

## 3. Testing Your Live REST Endpoint

With the terminal session active, open any web browser and hit your endpoint to verify the request lifecycle:

- **URL:** `http://localhost:8080/hello`
- **Expected Browser Response:**
  `Spring Boot server is officially up and running!`

---

## 4. Stopping the Server

To shut down the live web server instance and free up port `8080`:

1. Click into your active terminal pane inside VS Code.
2. Press **`Ctrl + C`** on your keyboard.
3. If prompted to terminate the batch job, type **`Y`** and hit **Enter**.
