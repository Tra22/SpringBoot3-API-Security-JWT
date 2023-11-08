package com.tra22.security.controller;

import com.tra22.security.payload.global.Response;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<Response<?>> get() {
        return new ResponseEntity<>(Response.ok("GET:: admin controller"), HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public ResponseEntity<Response<?>> post() {
        return new ResponseEntity<>(Response.ok("POST:: admin controller"), HttpStatus.OK);
    }
    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public ResponseEntity<Response<?>> put() {
        return new ResponseEntity<>(Response.ok( "PUT:: admin controller"), HttpStatus.OK);
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public ResponseEntity<Response<?>> delete() {
        return new ResponseEntity<>(Response.ok( "DELETE:: admin controller"), HttpStatus.OK);
    }
}
