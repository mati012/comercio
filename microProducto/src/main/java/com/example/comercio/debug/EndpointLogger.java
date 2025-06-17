package com.example.comercio.debug;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class EndpointLogger {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @PostConstruct
    public void printEndpoints() {
        System.out.println("📋 ENDPOINTS REGISTRADOS:");
        handlerMapping.getHandlerMethods().forEach((key, value) -> {
            System.out.println("➡️ " + key);
        });
    }
}
