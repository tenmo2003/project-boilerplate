package com.tenmo.boilerplate.authentication;

import com.tenmo.boilerplate.shared.http.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping
    public ApiResponse authenticate(@RequestBody CredentialsDTO dto) {
        return new ApiResponse(HttpStatus.OK.value(), "Authenticated", authenticationService.authenticate(dto));
    }

    @PostMapping("/signup")
    public ApiResponse signup(@RequestBody SignUpDTO dto) {
        authenticationService.signup(dto);
        return new ApiResponse(HttpStatus.OK.value(), "Signed up");
    }
}
