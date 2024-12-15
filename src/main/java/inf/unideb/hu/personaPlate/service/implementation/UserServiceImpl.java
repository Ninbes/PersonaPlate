package inf.unideb.hu.personaPlate.service.implementation;

import inf.unideb.hu.personaPlate.data.entity.UserEntity;
import inf.unideb.hu.personaPlate.data.repository.UserRepository;
import inf.unideb.hu.personaPlate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

//    @Override
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//                return repository.findByEmail(username);
//            }
//        };
//    }
@Override
public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            UserEntity user = repository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            return new CustomUserDetails(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        }
    };
}
    public UserEntity getAuthenticatedUser() {
        // Get the authenticated user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long userId = userDetails.getId();
            return repository.findById(userId)
                    .orElseThrow(() -> new IllegalStateException("Authenticated user not found in the database."));
        }

        throw new IllegalStateException("User is not authenticated.");
    }

//    public UserEntity getAuthenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
//        String username = userEntity.getEmail();
//        return repository.findByEmail(username);
//    }
}
