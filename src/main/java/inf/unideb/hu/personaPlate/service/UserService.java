package inf.unideb.hu.personaPlate.service;

import inf.unideb.hu.personaPlate.data.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    UserEntity getAuthenticatedUser();
}
