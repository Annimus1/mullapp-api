package dev.pablo.mullapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/api") 
public class HealthController { 

    @GetMapping("/health") 
    public Map<String,String> health_check(){
        Map<String, String> response = new HashMap<String,String>();
        response.put("Name", "Mullapp");
        response.put("Version", "1.0.0");
        response.put("Status", "Active");
        return response;
    }
}
