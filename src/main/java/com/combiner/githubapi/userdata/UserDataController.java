package com.combiner.githubapi.userdata;

import com.combiner.githubapi.model.UserData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/userdata")
public class UserDataController {
    private final UserDataHandler userDataHandler;

    // Covers the most basic GitHub username restrictions.
    // see https://docs.github.com/en/enterprise-cloud@latest/admin/managing-iam/iam-configuration-reference/username-considerations-for-external-authentication
    static final String GITHUB_USERNAME_VALIDATION_REGEX = "^[a-zA-Z0-9-]{0,39}$";

    @Autowired
    UserDataController(UserDataHandler userDataHandler) {
        this.userDataHandler = userDataHandler;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Mono<UserData>> getUserData(@PathVariable("username") @NotBlank @Pattern(regexp = GITHUB_USERNAME_VALIDATION_REGEX) String username) {
        return ResponseEntity.ok(userDataHandler.getUserData(username));
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<?> handleException(WebClientResponseException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body("Error from GitHub: " + e.getMessage());
    }
}
