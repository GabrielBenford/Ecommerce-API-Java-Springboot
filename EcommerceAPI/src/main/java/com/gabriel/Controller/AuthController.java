package com.gabriel.Controller;


import com.gabriel.config.SecurityConfiguration;
import com.gabriel.config.TokenProvider;
import com.gabriel.dto.LoginRequestDTO;
import com.gabriel.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "Endpoints for user authentication")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

@PostMapping("/login")
@Operation(summary = "Make a login and receive a JWT token")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Customer created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized access"),
        @ApiResponse(responseCode = "409", description = "Some of the data already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")})
public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.email(),
                    loginRequestDTO.password()
            )
    );
    String token = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new LoginResponseDTO(token));
}
}

