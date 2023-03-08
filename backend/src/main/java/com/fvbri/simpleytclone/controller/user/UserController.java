package com.fvbri.simpleytclone.controller.user;

import com.fvbri.simpleytclone.model.response.Response;
import com.fvbri.simpleytclone.service.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Response> register()  {

        userService.saveUser();

        return ResponseEntity
                .ok()
                .body(
                        Response.builder()
                                .success(true)
                                .message("The video was updated correctly")
                                .build()
                );
    }
}
