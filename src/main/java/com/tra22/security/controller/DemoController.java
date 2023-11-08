package com.tra22.security.controller;

import com.tra22.security.payload.global.Response;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@Hidden
public class DemoController {
  @GetMapping
  public ResponseEntity<Response<?>> sayHello() {
    return ResponseEntity.ok(Response.ok("Hello from secured endpoint"));
  }
}
