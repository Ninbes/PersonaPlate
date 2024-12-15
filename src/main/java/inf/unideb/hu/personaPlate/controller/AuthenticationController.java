package inf.unideb.hu.personaPlate.controller;

import inf.unideb.hu.personaPlate.data.entity.UserEntity;
import inf.unideb.hu.personaPlate.data.repository.UserRepository;
import inf.unideb.hu.personaPlate.service.AuthenticationService;
import inf.unideb.hu.personaPlate.service.UserService;
import inf.unideb.hu.personaPlate.service.dto.LoginDto;
import inf.unideb.hu.personaPlate.service.dto.RegistrationDto;
import inf.unideb.hu.personaPlate.service.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin(origins = "localhost4200")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


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

        // Check if the authenticated user is the same as the user trying to delete their account
        if (authenticatedUser.getId().equals(userId)) {
            authenticationService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You cannot delete another user's account");
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto dto) {
        // Fetch the authenticated user
        UserEntity authenticatedUser = userService.getAuthenticatedUser();

        // Perform updates
        authenticatedUser.setName(dto.getName());
        authenticatedUser.setEmail(dto.getEmail());
        authenticatedUser.setPassword(passwordEncoder.encode(dto.getPassword())); // Encode password

        // Persist the changes to the database
        userRepository.save(authenticatedUser);

        return ResponseEntity.ok("User updated successfully");
    }
}
