package dbiadun.MultiplexApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @GetMapping("/")
    public String getExampleResponse() {
        return "Hello, World!";
    }
}
