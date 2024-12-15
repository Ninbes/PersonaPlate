package inf.unideb.hu.personaPlate.configuration;

import inf.unideb.hu.personaPlate.data.entity.RoleEntity;
import inf.unideb.hu.personaPlate.data.entity.UserEntity;
import inf.unideb.hu.personaPlate.data.repository.RoleRepository;
import inf.unideb.hu.personaPlate.data.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class DataInit {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    private void initializeAdminUser() {
        // Check if the admin user already exists
        if (userRepository.findByEmail("admin@admin.com") != null) {
            return;
        }

        // Fetch or create the ROLE_ADMIN role
        RoleEntity adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new RoleEntity(null, "ROLE_ADMIN");
            adminRole = roleRepository.save(adminRole); // Persist the role
        }

        // Create the admin user
        UserEntity adminUser = new UserEntity();
        adminUser.setName("Admin");
        adminUser.setEmail("admin@admin.com");
        adminUser.setPassword(passwordEncoder.encode("admin123")); // Secure default password
        adminUser.setRoles(Set.of(adminRole)); // Assign the ROLE_ADMIN role

        // Persist the admin user
        userRepository.save(adminUser);

        System.out.println("Admin user created with email: admin@admin.com and password: admin123");
    }
}
