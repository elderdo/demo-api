# How Spring Boot Determines Request Routing

This document explains the internal mechanics of how Spring Boot maps an incoming web browser request to your custom Java code in the `demo-api` project.

---

## 1. The Core Architecture (The Controller Setup)

Your `HelloController.java` file utilizes two critical Spring Framework annotations that tell the engine how to register and expose your web endpoints.

```java
package com.elderdo.demo_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Spring Boot server is officially up and running!";
    }
}
```

### Breakdown of the Component Roles:

- **`@RestController`**: This is a combination of `@Controller` and `@ResponseBody`. It tells Spring's component scanner that this class handles web requests and that whatever string or data the method returns should be sent directly back to the browser as raw data (like plain text or JSON), rather than looking for a frontend HTML template file.
- **`@GetMapping("/hello")`**: This explicitly maps a specific HTTP protocol method (**GET**) and a specific URL path fragment (**`/hello`**) to the execution of the `sayHello()` method.

---

## 2. The Internal Routing Lifecycle (Step-by-Step)

When you type `http://localhost:8080/hello` into your browser, the following sequential process happens behind the scenes:

### Step A: The Embedded Server Captures the Request

1. Your embedded **Apache Tomcat** web server listens on port `8080`.
2. When a network request hits that port, Tomcat receives the raw network packet and parses out the path destination: `/hello`.

### Step B: The DispatcherServlet Takes Control

1. Tomcat hands the request off to Spring Boot's central hub, the **`DispatcherServlet`**.
2. The `DispatcherServlet` acts as the front door controller for your entire application. It doesn't know how to process your logic directly; instead, it coordinates who _should_ handle it.

### Step C: Consulting the Handler Mapping

1. The `DispatcherServlet` queries a registry component called the **`HandlerMapping`**.
2. Think of `HandlerMapping` as an internal lookup table. During application startup, it scanned your project, saw `@GetMapping("/hello")`, and created a record matching the path `/hello` directly to `HelloController.sayHello()`.
3. The `HandlerMapping` returns a pointer to your method back to the `DispatcherServlet`.

### Step D: Execution and Response Delivery

1. The `DispatcherServlet` invokes your `sayHello()` method.
2. The method returns the Java String: `"Spring Boot server is officially up and running!"`.
3. The `DispatcherServlet` wraps this string inside an HTTP response packet, sets a status code of `200 OK`, and passes it back to Tomcat, which streams the text straight to your web browser screen.

---

## 3. Visual Routing Map

```text
 [ Web Browser ]
       │
       ▼ (Requests: http://localhost:8080/hello)
 [ Tomcat Server ]
       │
       ▼ (Hand-off)
 [ DispatcherServlet ] <───► [ HandlerMapping ] (Looks up who owns "/hello")
       │
       ▼ (Invokes Method)
 [ HelloController.sayHello() ]
       │
       ▼ (Returns "Spring Boot server...")
 [ Web Browser Screen Displays Text ]
```
