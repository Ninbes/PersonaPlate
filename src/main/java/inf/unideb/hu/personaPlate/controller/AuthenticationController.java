package inf.unideb.hu.personaPlate.controller;

import inf.unideb.hu.personaPlate.data.entity.UserEntity;
import inf.unideb.hu.personaPlate.service.AuthenticationService;
import inf.unideb.hu.personaPlate.service.UserService;
import inf.unideb.hu.personaPlate.service.dto.LoginDto;
import inf.unideb.hu.personaPlate.service.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public String registration(@RequestBody RegistrationDto dto) { return authenticationService.registration(dto); }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto dto) { return authenticationService.login(dto); }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        UserEntity authenticatedUser = userService.getAuthenticatedUser();
        if (authenticatedUser.getId().equals(userId)) {
            authenticationService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You cannot delete another user's account");
    }
}
