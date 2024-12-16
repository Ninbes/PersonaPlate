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

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser() {
        UserEntity authenticatedUser = userService.getAuthenticatedUser();

        if (authenticatedUser != null) {
            authenticationService.deleteUser(authenticatedUser.getId());
            return ResponseEntity.ok("User deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You cannot delete another user's account");
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto dto) {
        UserEntity authenticatedUser = userService.getAuthenticatedUser();

        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            authenticatedUser.setName(dto.getName());
        }

        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            authenticatedUser.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            authenticatedUser.setPassword(passwordEncoder.encode(dto.getPassword())); // Encode password
        }

        userRepository.save(authenticatedUser);

        return ResponseEntity.ok("User updated successfully");
    }
}
