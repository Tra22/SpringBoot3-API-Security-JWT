package com.tra22.security.controller;

import com.tra22.security.payload.global.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {
    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @GetMapping
    public ResponseEntity<Response<?>> get() {
        return ResponseEntity.ok(Response.ok("GET:: management controller"));
    }
    @PostMapping
    public ResponseEntity<Response<?>> post() {
        return ResponseEntity.ok(Response.ok("POST:: management controller"));
    }
    @PutMapping
    public ResponseEntity<Response<?>> put() {
        return ResponseEntity.ok(Response.ok("PUT:: management controller"));
    }
    @DeleteMapping
    public ResponseEntity<Response<?>> delete() {
        return ResponseEntity.ok(Response.ok("DELETE:: management controller"));
    }
}
