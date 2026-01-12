package app.notificadorweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "Notificador Web UP";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
